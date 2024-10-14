package supconmodel;

import supconmodel.model.agents.Good;
import supconmodel.model.gui.controller.WindowController;
import supconmodel.model.simulation.Simulator;
import supconmodel.utils.Enums.*;
import supconmodel.utils.Parameters;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

public class SupConModelMain {
    public static WindowController windowController;
    public static ArrayList<Good> allGoods;
    public static int maxGoodID = -1;
    public static Properties properties = new Properties();
    public static File propertiesFile;
    public static File goodsFile;
    public static Simulator simulator = new Simulator();

    public static void main(String[] args) throws IOException, FontFormatException {
        try (InputStream fontStream = SupConModelMain.class.getResourceAsStream("/font/JetBrainsMono-Regular.ttf")) {
            if (fontStream == null) {
                System.out.println("Font not found!");
                return;
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        }

        windowController = new WindowController();
        windowController.openSelectWindow(WindowEnum.MAIN);
    }

    public static void SetUpParameters(InputStream input) throws IOException {
        if (input == null) {
            System.out.println("Properties not found!");
            return;
        }

        properties.load(input);
        Parameters.init_l1_ratio = Double.parseDouble(properties.getProperty("init_l1_ratio"));
        Parameters.init_l2_ratio = Double.parseDouble(properties.getProperty("init_l2_ratio"));
        Parameters.init_l3_ratio = Double.parseDouble(properties.getProperty("init_l3_ratio"));

        Parameters.goods_per_supplier = Integer.parseInt(properties.getProperty("goods_per_supplier"));
        Parameters.num_consumers = Integer.parseInt(properties.getProperty("num_consumers"));
        Parameters.num_suppliers = Integer.parseInt(properties.getProperty("num_suppliers"));
        Parameters.init_deposit_multiplier = Double.parseDouble(properties.getProperty("init_deposit_multiplier"));

        Parameters.l1_max_basicDemand = Double.parseDouble(properties.getProperty("l1_max_basicDemand"));
        Parameters.l1_max_convenientDemand = Double.parseDouble(properties.getProperty("l1_max_convenientDemand"));
        Parameters.l1_max_luxuryDemand = Double.parseDouble(properties.getProperty("l1_max_luxuryDemand"));
        Parameters.l1_init_basicDemand = Double.parseDouble(properties.getProperty("l1_init_basicDemand"));
        Parameters.l1_init_convenientDemand = Double.parseDouble(properties.getProperty("l1_init_convenientDemand"));
        Parameters.l1_init_luxuryDemand = Double.parseDouble(properties.getProperty("l1_init_luxuryDemand"));
        Parameters.l1_init_profit = Double.parseDouble(properties.getProperty("l1_init_profit"));
        Parameters.l1_add_basicDemand = Double.parseDouble(properties.getProperty("l1_add_basicDemand"));
        Parameters.l1_add_convenientDemand = Double.parseDouble(properties.getProperty("l1_add_convenientDemand"));
        Parameters.l1_add_luxuryDemand = Double.parseDouble(properties.getProperty("l1_add_luxuryDemand"));

        Parameters.l2_max_basicDemand = Double.parseDouble(properties.getProperty("l2_max_basicDemand"));
        Parameters.l2_max_convenientDemand = Double.parseDouble(properties.getProperty("l2_max_convenientDemand"));
        Parameters.l2_max_luxuryDemand = Double.parseDouble(properties.getProperty("l2_max_luxuryDemand"));
        Parameters.l2_init_basicDemand = Double.parseDouble(properties.getProperty("l2_init_basicDemand"));
        Parameters.l2_init_convenientDemand = Double.parseDouble(properties.getProperty("l2_init_convenientDemand"));
        Parameters.l2_init_luxuryDemand = Double.parseDouble(properties.getProperty("l2_init_luxuryDemand"));
        Parameters.l2_init_profit = Double.parseDouble(properties.getProperty("l2_init_profit"));
        Parameters.l2_add_basicDemand = Double.parseDouble(properties.getProperty("l2_add_basicDemand"));
        Parameters.l2_add_convenientDemand = Double.parseDouble(properties.getProperty("l2_add_convenientDemand"));
        Parameters.l2_add_luxuryDemand = Double.parseDouble(properties.getProperty("l2_add_luxuryDemand"));

        Parameters.l3_max_basicDemand = Double.parseDouble(properties.getProperty("l3_max_basicDemand"));
        Parameters.l3_max_convenientDemand = Double.parseDouble(properties.getProperty("l3_max_convenientDemand"));
        Parameters.l3_max_luxuryDemand = Double.parseDouble(properties.getProperty("l3_max_luxuryDemand"));
        Parameters.l3_init_basicDemand = Double.parseDouble(properties.getProperty("l3_init_basicDemand"));
        Parameters.l3_init_convenientDemand = Double.parseDouble(properties.getProperty("l3_init_convenientDemand"));
        Parameters.l3_init_luxuryDemand = Double.parseDouble(properties.getProperty("l3_init_luxuryDemand"));
        Parameters.l3_init_profit = Double.parseDouble(properties.getProperty("l3_init_profit"));
        Parameters.l3_add_basicDemand = Double.parseDouble(properties.getProperty("l3_add_basicDemand"));
        Parameters.l3_add_convenientDemand = Double.parseDouble(properties.getProperty("l3_add_convenientDemand"));
        Parameters.l3_add_luxuryDemand = Double.parseDouble(properties.getProperty("l3_add_luxuryDemand"));

        Parameters.w_d = Double.parseDouble(properties.getProperty("w_d"));
        Parameters.w_p = Double.parseDouble(properties.getProperty("w_p"));
        Parameters.w_h = Double.parseDouble(properties.getProperty("w_h"));
        Parameters.w_c = Double.parseDouble(properties.getProperty("w_c"));

        Parameters.prod_a = Double.parseDouble(properties.getProperty("prod_a"));
        Parameters.prod_basic = Double.parseDouble(properties.getProperty("prod_basic"));
        Parameters.prod_convenient = Double.parseDouble(properties.getProperty("prod_convenient"));
        Parameters.prod_luxury = Double.parseDouble(properties.getProperty("prod_luxury"));

        Parameters.init_basic_inventory = Integer.parseInt(properties.getProperty("init_basic_inventory"));
        Parameters.init_convenient_inventory = Integer.parseInt(properties.getProperty("init_convenient_inventory"));
        Parameters.init_luxury_inventory = Integer.parseInt(properties.getProperty("init_luxury_inventory"));

        Parameters.init_basic_max_inventory = Integer.parseInt(properties.getProperty("init_basic_max_inventory"));
        Parameters.init_convenient_max_inventory = Integer.parseInt(properties.getProperty("init_convenient_max_inventory"));
        Parameters.init_luxury_max_inventory = Integer.parseInt(properties.getProperty("init_luxury_max_inventory"));
    }

    public static void SaveProperties(){
        try{
            properties.setProperty("l1_init_basicDemand", String.valueOf(Parameters.l1_init_basicDemand));
            properties.setProperty("l1_init_convenientDemand", String.valueOf(Parameters.l1_init_convenientDemand));
            properties.setProperty("l1_init_luxuryDemand", String.valueOf(Parameters.l1_init_luxuryDemand));
            properties.setProperty("l1_init_profit", String.valueOf(Parameters.l1_init_profit));

            properties.setProperty("l2_init_basicDemand", String.valueOf(Parameters.l2_init_basicDemand));
            properties.setProperty("l2_init_convenientDemand", String.valueOf(Parameters.l2_init_convenientDemand));
            properties.setProperty("l2_init_luxuryDemand", String.valueOf(Parameters.l2_init_luxuryDemand));
            properties.setProperty("l2_init_profit", String.valueOf(Parameters.l2_init_profit));

            properties.setProperty("l3_init_basicDemand", String.valueOf(Parameters.l3_init_basicDemand));
            properties.setProperty("l3_init_convenientDemand", String.valueOf(Parameters.l3_init_convenientDemand));
            properties.setProperty("l3_init_luxuryDemand", String.valueOf(Parameters.l3_init_luxuryDemand));
            properties.setProperty("l3_init_profit", String.valueOf(Parameters.l3_init_profit));

            properties.setProperty("w_d", String.valueOf(Parameters.w_d));
            properties.setProperty("w_p", String.valueOf(Parameters.w_p));
            properties.setProperty("w_h", String.valueOf(Parameters.w_h));
            properties.setProperty("w_c", String.valueOf(Parameters.w_c));

            properties.setProperty("prod_a", String.valueOf(Parameters.prod_a));
            properties.setProperty("prod_basic", String.valueOf(Parameters.prod_basic));
            properties.setProperty("prod_convenient", String.valueOf(Parameters.prod_convenient));
            properties.setProperty("prod_luxury", String.valueOf(Parameters.prod_luxury));

            createBackup(propertiesFile);
            try (FileOutputStream output = new FileOutputStream(propertiesFile)){
                properties.store(output, "Update Properties");
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void SetUpGoodData(InputStream input) {
        allGoods = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                if (id > maxGoodID) {
                    maxGoodID = id;
                }
                String name = parts[1];
                GoodType type;
                switch (parts[2]) {
                    case "0" -> type = GoodType.BASIC;
                    case "1" -> type = GoodType.CONVENIENCE;
                    case "2" -> type = GoodType.LUXURY;
                    default -> {
                        System.out.println("Unknown GoodType " + parts[2]);
                        return;
                    }
                }
                float cost = Float.parseFloat(parts[3]);
                float expectedProfitability = Float.parseFloat(parts[4]);
                float basic = Float.parseFloat(parts[5]);
                float convenience = Float.parseFloat(parts[6]);
                float luxury = Float.parseFloat(parts[7]);

                Good newGood = new Good(id, name, type, cost, expectedProfitability, basic, convenience, luxury);
                allGoods.add(newGood);

            }
        } catch (IOException e) {
            throw new RuntimeException("Unexpected error occurred when initializing the goods data!");
        }
    }

    public static void SaveGoods() {
        createBackup(goodsFile);
        try(PrintWriter writer = new PrintWriter(new FileWriter(goodsFile, false))){
            for(Good good: allGoods){
                writer.println(good.toString());
            }
        }catch (IOException e) {
            throw new RuntimeException("Unexpected error occurred when saving the goods data!");
        }
    }

    private static void createBackup(File originalFile) {
        File parentDirectory = originalFile.getParentFile();

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);

        File backupDirectory = new File(parentDirectory, "backup");
        if (!backupDirectory.exists()) {
            backupDirectory.mkdir();
        }

        String originalFileName = originalFile.getName();
        int dotIndex = originalFileName.lastIndexOf(".");

        String baseName = (dotIndex != -1) ? originalFileName.substring(0, dotIndex) : originalFileName;
        String extension = (dotIndex != -1) ? originalFileName.substring(dotIndex) : "";


        File backupFile = new File(backupDirectory, baseName + "_"  + formattedDateTime + extension + ".bak");

        try {
            Files.copy(originalFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Fail to create a backup file: " + e.getMessage());
        }
    }
}