import java.util.LinkedList;
import java.util.Scanner;
import java.io.IOException;
import java.io.IOException;
import java.util.regex.Pattern;

public class Main {

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static <T> void main(String[] args)throws Exception {

        KWSingleLinkedList list = new KWSingleLinkedList();
        while (true){
            Scanner input = new Scanner(System.in);
            System.out.println("****************");
            System.out.println("(A)dd");
            System.out.println("(D)elete");
            System.out.println("(E)mail search");
            System.out.println("(P)rint list");
            System.out.println("(S)earch");
            System.out.println("(Q)uit");
            System.out.println("****************");
            System.out.print("Enter a command: ");
            String command = input.nextLine().toLowerCase();
            switch (command){
                case "a":
                    System.out.print("Enter name: ");
                    String name = input.nextLine();

                    System.out.print("Enter number: ");
                    String number = input.nextLine();
                    boolean c1;
                    String email;
                    do{
                        System.out.print("Enter email: ");
                        email = input.nextLine();
                        c1 = isValid(email);
                    }while (c1 == false);
                    list.add(name, number, email);
                    System.out.println();
                    break;
                case "d":
                    System.out.print("Enter name: ");
                    name = input.nextLine();
                    if(list.remove(name)) System.out.println("Successfully deleted contact!");
                    else System.out.println("Contact doesn't exist!");
                    System.out.println();
                    break;
                case "e":
                    System.out.println("Enter email: ");
                    name = input.nextLine();
                    System.out.println();
                    if(list.contains(name)) list.info(name);
                    else System.out.println("Email not registered!");
                    System.out.println();
                    break;
                case "p":
                    System.out.println(list.toString());
                    break;
                case "s":
                    System.out.print("Enter name: ");
                    name = input.nextLine();
                    System.out.println();
                    if(list.contains(name)) list.info(name);
                    else System.out.println("Contact not registered!");
                    System.out.println();
                    break;
                case "q":
                    System.exit(0);
                    break;
            }
        }
    }
}
