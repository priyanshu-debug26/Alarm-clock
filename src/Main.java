import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String [] args){

        // ALARM CLOCK
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime = null;
        String filePath = "a minute lofi.wav";

        while(alarmTime == null){
            try {
                System.out.print("Enter the Alarm time (HH:MM:SS) : ");
                String inTime = sc.nextLine();

                alarmTime =LocalTime.parse(inTime, dtf);
                System.out.print("Alarm set for " + alarmTime + "\n");

            }
            catch (DateTimeParseException e){
                System.out.println("Enter a valid time for Alarm!!");
            }
        }

        AlarmClock alarmClock = new AlarmClock(alarmTime, filePath, sc);
        Thread alarmThread = new Thread(alarmClock);
        alarmThread.start();

    }
}
