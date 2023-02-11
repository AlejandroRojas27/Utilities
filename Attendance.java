import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Attendance {
    public static void main(String[] args) {

        if(args.length > 2){
            System.out.println("Usa el programa de la manera sigiente: \nNombreDelEmpleado CargoDelEmpleado");
            System.exit(1);
        }

        File list = new File("C:\\Users\\juan\\IdeaProjects\\Utilities\\src\\Lista.txt");

        ArrayList<String> data = textFileToArrayList(list);

        String name = "Nombre: " + args[0] + " Cargo: " + args[1]; //NAME

        ArrayList<String> update = today(name, data);

        updateTheFile(list, data, update);

    }

    public static ArrayList<String> textFileToArrayList(File list) {

        ArrayList<String> data = new ArrayList<>();

        try (
                Scanner input = new Scanner(list)
        ) {
            while (input.hasNext()) {
                data.add(input.nextLine());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return data;


    } // READ THE .txt file and pass the info to an arrayList

    public static ArrayList<String> today(String name, ArrayList<String> data) {

        Calendar calendar = new GregorianCalendar();
        int weekOfTheYear = calendar.get(Calendar.WEEK_OF_YEAR);


        String semana = "Semana: " + weekOfTheYear;
        String fecha = "Fecha: " + switch (calendar.get(Calendar.MONTH)) {
            case 0 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Enero" + "/" + calendar.get(Calendar.YEAR);
            case 1 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Febrero" + "/" + calendar.get(Calendar.YEAR);
            case 2 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Marzo" + "/" + calendar.get(Calendar.YEAR);
            case 3 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Abril" + "/" + calendar.get(Calendar.YEAR);
            case 4 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Mayo" + "/" + calendar.get(Calendar.YEAR);
            case 5 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Junio" + "/" + calendar.get(Calendar.YEAR);
            case 6 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Julio" + "/" + calendar.get(Calendar.YEAR);
            case 7 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Agosto" + "/" + calendar.get(Calendar.YEAR);
            case 8 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Septiembre" + "/" + calendar.get(Calendar.YEAR);
            case 9 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Octubre" + "/" + calendar.get(Calendar.YEAR);
            case 10 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Noviembre" + "/" + calendar.get(Calendar.YEAR);
            case 11 -> calendar.get(Calendar.DAY_OF_MONTH) + "/Diciembre" + "/" + calendar.get(Calendar.YEAR);
            default -> "";
        };


        ArrayList<String> update = new ArrayList<>();
        ArrayList<String> todayData = new ArrayList<>();

        // To check if yhe file already contains semana field
        if (!data.contains(semana)) {
            update.add(semana);
        }

        // To check if yhe file already contains fecha field
        if (!data.contains(fecha)) {
            update.add(fecha);
        } else {
            for (int i = data.indexOf(fecha); i < data.size(); i++) {
                todayData.add(data.get(i));
            }
        }


        // To check if the file already contains the name
        if (!todayData.contains(name)) {
            update.add(name);
        } else {
            System.out.println("El nombre ya esta en la lista de hoy.");
            System.exit(2);
        }

        return update;
    } // TO UPDATE LIST

    public static void updateTheFile(File list, ArrayList<String> data, ArrayList<String> update) {

        try (
                PrintWriter output = new PrintWriter(list)
        ) {

            for (int i = 0; i < data.size(); i++) {
                output.println(data.get(i));
            }

            for (int i = 0; i < update.size(); i++) {
                output.println(update.get(i));
            }

            System.out.println("Lista actualizada.");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    } // To update the .txt file with the new data

}
