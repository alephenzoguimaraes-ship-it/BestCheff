package impressions;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultPrintServiceResolver.
 */
public class DefaultPrintServiceResolver {

    /**
     * Resolve.
     *
     * @return the prints the service
     */
    public static PrintService resolve() {
        if (isWindows()) {
            PrintService windowsDefault = findWindowsDefaultPrintService();
            if (windowsDefault != null) {
                return windowsDefault;
            }
        }
        return PrintServiceLookup.lookupDefaultPrintService();
    }

    /**
     * Checks if is windows.
     *
     * @return true, if is windows
     */
    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    /**
     * Find windows default print service.
     *
     * @return the prints the service
     */
    private static PrintService findWindowsDefaultPrintService() {
        String defaultPrinterName = readWindowsDefaultPrinterName();

        if (defaultPrinterName == null || defaultPrinterName.isBlank()) {
            return null;
        }

        for (PrintService service : PrintServiceLookup.lookupPrintServices(null, null)) {
            if (service.getName().equalsIgnoreCase(defaultPrinterName.trim())) {
                return service;
            }
        }

        return null;
    }

    /**
     * Read windows default printer name.
     *
     * @return the string
     */
    private static String readWindowsDefaultPrinterName() {
        try {
            Process process = new ProcessBuilder(
                "powershell.exe",
                "-NoProfile",
                "-Command",
                "(Get-CimInstance -ClassName Win32_Printer -Filter \"Default = TRUE\").Name"
            ).redirectErrorStream(true).start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String printerName = reader.readLine();
                boolean finished = process.waitFor(5, TimeUnit.SECONDS);

                if (!finished) {
                    process.destroyForcibly();
                    return null;
                }

                return printerName;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao consultar impressora padrão do Windows via PowerShell: " + e.getMessage());
            return null;
        }
    }
}
