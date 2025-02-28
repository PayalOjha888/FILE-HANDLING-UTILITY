import java.util.Scanner;
import java.io.*;

public class FileHandling {

    public void insertData(File file, Scanner sc) throws Exception { 
        PrintWriter out = new PrintWriter(new FileWriter(file, true)); // Append mode
        int flag = 1;
        
        while (flag == 1) {
            System.out.println("Enter the data to insert: ");
            String data = sc.nextLine();
            out.println(data);
            out.flush();
            System.out.println("Want to insert more data? (0/1)");
            flag = sc.nextInt();
            sc.nextLine(); // Consume the newline
        } 
        out.close();
    }

    public void readData(File file) throws Exception { 
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("\nFile reading completed!");
        br.close();
    }

    public void updateFile(File file, Scanner sc) throws Exception { 
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, false)); // Overwrite mode
        int flag=1;
        while (flag==1) {
            System.out.println("Insert data you want to insert into the file:");
            String data = sc.nextLine();
            if (data.equalsIgnoreCase("EXIT")) 
            { // User can type EXIT to stop
                break;
            }
            System.out.println("Want to insert more data? (0/1)");
            flag = sc.nextInt();
            sc.nextLine();
            bw.write(data);
            bw.newLine();
        }
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        FileHandling obj = new FileHandling();
        Scanner sc = new Scanner(System.in);

        System.out.println("File Handling project!");
        System.out.println("Insert the file name (in .txt format): ");
        String fname = sc.nextLine();

        File file = new File(fname);
        if (file.createNewFile()) {
            System.out.println("New file created: " + file.getName());
        } else {
            System.out.println("File already exists.");
        }

        int choice;
        do {
            System.out.println("\nPress :\n1 for Inserting data into the file" +
                    "\n2 for Reading the data from the file" +
                    "\n3 for Updating the file data" +
                    "\n4 to create a new file" +
                    "\n0 for exit");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    obj.insertData(file, sc);
                    break;
                case 2:
                    obj.readData(file);
                    break;
                case 3:
                    obj.updateFile(file, sc);
                    break;
                case 4:
                    System.out.println("Enter new file name: ");
                    String newFileName = sc.nextLine();
                    file = new File(newFileName);
                    if (file.createNewFile()) {
                        System.out.println("New file created: " + newFileName);
                    } else {
                        System.out.println("File already exists.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Choice!!");
                    break;
            }
        } while (choice != 0);
        sc.close();
    }
}
