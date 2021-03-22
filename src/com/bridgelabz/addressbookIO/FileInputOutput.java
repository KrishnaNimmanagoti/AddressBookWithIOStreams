package com.bridgelabz.addressbookIO;

import java.io.*;
import java.util.*;

public class FileInputOutput {

    static Scanner scan = new Scanner(System.in);
    static String fileAddress;
    static String fileType;

        private static void addAddressBook() {
            System.out.println("Enter Address Book name: ");
            String addressBookName = scan.next();
            File filePath = new File("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + addressBookName);
            filePath.mkdir();
            System.out.println("Address Book creation is successfull with name " + addressBookName + "\n");
        }

        private static void showAddressBooks() {
            System.out.println("List of Address Books:");
            String dirPath = "C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams";
            File dir = new File(dirPath);
            String[] files = dir.list();
            assert files != null;
            if (files.length == 0) {
                System.out.println("The directory is empty");
            } else {
                for (String aFile : files) {
                    System.out.println(aFile);
                }
            }
            System.out.println();
        }

        public static ArrayList<String> takeInput() {
            ArrayList<String> contactList = new ArrayList<>();
            System.out.println("Enter first name: ");
            String firstName = scan.next();
            contactList.add(firstName);
            System.out.println("Enter last name: ");
            contactList.add(scan.next());
            System.out.println("Enter  address: ");
            contactList.add(scan.next());
            System.out.println("Enter city: ");
            contactList.add(scan.next());
            System.out.println("Enter state: ");
            contactList.add(scan.next());
            System.out.println("Enter zip: ");
            contactList.add(scan.next());
            System.out.println("Enter mobile number: ");
            contactList.add(scan.next());
            System.out.println("Enter email: ");
            contactList.add(scan.next());
            return contactList;
        }

        private static void addContact() throws IOException {
            String addressBookName = scan.next();
            String dirPath = "C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams";
            File dir = new File(dirPath);
            String[] files = dir.list();
            assert files != null;
            if (files.length == 0) {
                System.out.println("The directory is empty");
            } else {
                for (String aFile : files) {
                    if (aFile.equals(addressBookName)) {
                        fileAddress = aFile;
                    }
                }
            }
            ArrayList<String> contactList = takeInput();
            File fileName = new File("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress +"\\" + contactList.get(0) +".txt");
            try {
                fileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileWriter writer = new FileWriter("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress +"\\" + contactList.get(0) +".txt");
            BufferedWriter buffer = new BufferedWriter(writer);
            for (String data : contactList){
                try {
                    buffer.write(data);
                    buffer.newLine();
                    buffer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            buffer.close();
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress +"\\" + contactList.get(0) +".txt");
                os = new FileOutputStream("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress +"\\" + contactList.get(0) +".csv");
                byte[] bufferd = new byte[1024];
                int length;
                while ((length = is.read(bufferd)) > 0) {
                    os.write(bufferd, 0, length);
                }
            } finally {
                assert is != null;
                is.close();
                assert os != null;
                os.close();
            }
            try {
                is = new FileInputStream("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress +"\\" + contactList.get(0) +".txt");
                os = new FileOutputStream("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress +"\\" + contactList.get(0) +".json");
                byte[] bufferd = new byte[1024];
                int length;
                while ((length = is.read(bufferd)) > 0) {
                    os.write(bufferd, 0, length);
                }
            } finally {
                is.close();
                os.close();
            }
        }

        public static void showContacts() {
            showAddressBooks();
            System.out.println("Enter the Address Book name from the above: ");
            String addressBookName = scan.next();
            String dirPath = "C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams";
            File dir = new File(dirPath);
            String[] files = dir.list();
            assert files != null;
            if (files.length == 0) {
                System.out.println("The directory is empty");
            } else {
                for (String aFile : files) {
                    if (aFile.equals(addressBookName)) {
                        fileAddress = aFile;
                    }
                }
            }
            String filePath = "C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress;
            File allfiles = new File(filePath);
            String[] filesList = allfiles.list();
            assert filesList != null;
            if (filesList.length == 0) {
                System.out.println("The directory is empty");
            } else {
                for (String aFile : filesList) {
                    System.out.println(aFile);
                }
            }
            System.out.println("Enter the File name from the above: ");
            String fileName = scan.next();
            try {
                FileReader f = new FileReader("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress
                        + "\\" + fileName + fileType);
                BufferedReader br = new BufferedReader(f);
                System.out.println("Contact full details:");
                System.out.println("1. First Name: " + br.readLine());
                System.out.println("2. Last Name: " + br.readLine());
                System.out.println("3. Address: " + br.readLine());
                System.out.println("4. City: " + br.readLine());
                System.out.println("5. State: " + br.readLine());
                System.out.println("6. Zip: " + br.readLine());
                System.out.println("7. Mobile number: " + br.readLine());
                System.out.println("8. Email: " + br.readLine());
                br.close();
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private static void deleteContact() {
        showAddressBooks();
        System.out.println("Enter the Address Book name from the above: ");
        String addressBookName = scan.next();

        String dirPath = "C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams";
        File dir = new File(dirPath);
        String[] files = dir.list();
        assert files != null;
        if (files.length == 0) {
            System.out.println("The directory is empty");
        } else {
            for (String aFile : files) {
                if (aFile.equals(addressBookName)) {
                    fileAddress = aFile;
                }
            }
        }
        String filePath = "C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress;
        File allfiles = new File(filePath);
        String[] filesList = allfiles.list();
        assert filesList != null;
        if (filesList.length == 0) {
            System.out.println("The directory is empty");
        } else {
            for (String aFile : filesList) {
                System.out.println(aFile);
            }
        }
        System.out.println("Enter the File name from the above: ");
        String fileName = scan.next();
        File myObj = new File("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress
                + "\\" + fileName + fileType);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }

    }

    public static void editContact() {
        deleteContact();
        ArrayList<String> contactList = takeInput();
        File filePath = new File("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress +"\\" + contactList.get(0) +".txt");
        try {
            filePath.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer;
        try {
            writer = new FileWriter("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + fileAddress +"\\" + contactList.get(0) + fileType);
            BufferedWriter buffer = new BufferedWriter(writer);
            for (String data : contactList){
                try {
                    buffer.write(data);
                    buffer.newLine();
                    buffer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchByCity() {
        System.out.println("Enter the city name: ");
        String city = scan.next();
        String dirPath = "C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams";
        File dir = new File(dirPath);
        String[] files = dir.list();
        assert files != null;
        for (String addressBook: files) {
            String filePath = "C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + addressBook;
            File allfiles = new File(filePath);
            String[] filesList = allfiles.list();
            assert filesList != null;
            for (String fileName : filesList) {
                 Scanner scan2;
                 try {
                     scan2 = new Scanner(new FileInputStream("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + addressBook + "\\" + fileName));
                     while(scan2.hasNextLine()) {
                         String line = scan2.nextLine();
                         if(line.contains(city)) {
                             FileReader file = new FileReader("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + addressBook + "\\" + fileName);
                             BufferedReader br = new BufferedReader(file);
                             System.out.println(br.readLine());
                         }
                     }
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
            }
        }
    }

    private static void sortingByPersons() {
        showAddressBooks();
        System.out.println("Select and enter name of AddressBook:\n");
        String addressBook = scan.next();
        String filePath = "C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + addressBook;
        File allfiles = new File(filePath);
        String[] filesList = allfiles.list();
        assert filesList != null;
        for (String fileName : filesList) {
            FileReader file = null;
            try {
                file = new FileReader("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + addressBook + "\\" + fileName);
                BufferedReader br = new BufferedReader(file);
                System.out.println(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sortByCityOrState() {
        showAddressBooks();
        System.out.println("Select and enter name of AddressBook:\n");
        String addressBook = scan.next();
        String filePath = "C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + addressBook;
        File allfiles = new File(filePath);
        String[] filesList = allfiles.list();
        System.out.println("Enter the city name: ");
        String city = scan.next();
        assert filesList != null;
        for (String fileName : filesList) {
            Scanner scan2;
            try {
                scan2 = new Scanner(new FileInputStream("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + addressBook + "\\" + fileName));
                while (scan2.hasNextLine()) {
                    String line = scan2.nextLine();
                    if (line.contains(city)) {
                        FileReader file = new FileReader("C:\\Users\\Ramesh\\Desktop\\AddressBookI_O_Streams\\" + addressBook + "\\" + fileName);
                        BufferedReader br = new BufferedReader(file);
                        System.out.println(br.readLine());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void fileType() {
        System.out.println("In which file type do you want enter the number: \n1) .txt file\n2) .csv file\n3) .json file");
        String choice = scan.next();
        switch (choice) {
            case "1" -> fileType = ".txt";
            case "2" -> fileType = ".csv";
            case "3" -> fileType = ".json";
            default -> System.out.println("no match found");
        }
    }

        public static void main(String[] args) throws IOException {
            String choice;
            do {
                System.out.println("********** Menu **********");
                System.out.println("1. Add Address Book");
                System.out.println("2. Show Address Books");
                System.out.println("3. Add Contact");
                System.out.println("4. Edit Contact");
                System.out.println("5. Delete Contact");
                System.out.println("6. Show Contacts");
                System.out.println("7. search by City");
                System.out.println("8. Persons in City Or State");
                System.out.println("9. Sort by city or state and show all Persons");
                System.out.println("10. Exit\\n");
                System.out.println("Press a digit to select the required option: ");
                choice = scan.next();
                int option = Integer.parseInt(choice);
                switch (option) {
                    case 1 -> addAddressBook();
                    case 2 -> showAddressBooks();
                    case 3 -> { showAddressBooks();
                        System.out.println("Enter the Address Book name from the above: ");
                        addContact();
                         }
                    case 4 -> {
                        fileType();
                        System.out.println("Enter the Address Book name to Edit from the above: ");
                        editContact();
                        }
                    case 5 ->{ fileType();
                            deleteContact();}
                    case 6 ->{fileType();
                            showContacts();}
                    case 7 -> {fileType();
                            searchByCity();}
                    case 8 -> { fileType();
                            sortingByPersons(); }
                    case 9 -> {fileType();
                            sortByCityOrState(); }
                    case 10 -> System.out.println("Program exited successfully.");
                }

            } while (!"10".equals(choice));
            scan.close();
        }
    }

