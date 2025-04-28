import java.util.*;

class MedicalRecord {

    private List<String> medicalEntries;
    MedicalRecord() {
        medicalEntries = new ArrayList<>();
    }

    public void addRecord(String record) {
        medicalEntries.add(record);
    }

    public List<String> retrieveRecord() {
        return medicalEntries;
    }

}

class Checkup {
    private int checkupNo;
    private boolean hasDiagnosis;

    Checkup(int checkupNo1, boolean Diagnosishas) {
        checkupNo = (checkupNo1);
        hasDiagnosis = Diagnosishas;
    }

    public int retrieveType() {
        return checkupNo;
    }

    public boolean retrieveDiagnosis() {
        return hasDiagnosis;
    }

}

class Patient {

    private String patientName;
    private int patientAge;
    int checkupType;

    private MedicalRecord patientHistory;

    public Patient(String name, int age, int checkupTypeNo) {

        patientName = name;
        patientAge = age;
        checkupType = checkupTypeNo;

        patientHistory = new MedicalRecord();
    }

    public void resultsRecords(Checkup newCheckup) {
        if(newCheckup.retrieveType()==1){
            patientHistory.addRecord(patientName+" "+patientAge+" "+"BP"+" "+newCheckup.retrieveDiagnosis()+" ");
            
        }
        if(newCheckup.retrieveType()==2){
            patientHistory.addRecord(patientName+" "+patientAge+" "+"SL"+" "+newCheckup.retrieveDiagnosis()+" ");
        }
        
        if(newCheckup.retrieveType()==3){
            patientHistory.addRecord(patientName+" "+patientAge+" "+"Cholesterol"+" "+newCheckup.retrieveDiagnosis()+" ");
        }
        
    }

    public List<String> retrieveMedicalRecord() {
        return patientHistory.retrieveRecord();
    }

}


class Doctor {

    private String name;
    Random rand = new Random();

    Doctor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name...");
        name = sc.nextLine();
        
    }

    public Checkup performCheckup(int typeofCheck) {
        boolean hasDiagnosis = rand.nextInt(2) == 1;
        Checkup newMade = new Checkup(typeofCheck, hasDiagnosis);
        return newMade;
    }

}


class Chemist {

    public static void providePrescription(Patient patient) {
        if(patient.retrieveMedicalRecord().size() == 0){
            System.out.println("No medical report generated yet. Please do checkup first...");
            return;
        } else {
            for(int i = 0; i < patient.retrieveMedicalRecord().size(); i++){
                String data[] = patient.retrieveMedicalRecord().get(i).split(" ");
                
                if(data[3].equals("false")){
                    System.out.println("You did not have any problem...");
                }
                else if(data[3].equals("true") && data[2].equals("BP")){
                    System.out.println("You need to take Telmisartan 40mg...");
                }
                else if(data[3].equals("true") && data[2].equals("SL")){
                    System.out.println("You need to take Metformin 50mg...");
                }
                else if(data[3].equals("true") && data[2].equals("Cholesterol")){
                    System.out.println("You need to take Atorvastatin A 75...");
                }
            }
        }
    }
}



public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Abhay's Hospital System....");
        List<Doctor> d = new ArrayList<>();
        List<Patient> allPatients = new ArrayList<>();
        int key = 0;
        Scanner scanner = new Scanner(System.in);
        while (key != 5) {
            System.out.println("Press 1 for Doctor register... ");
            System.out.println("Press 2 for patient portal..");
            System.out.println("Press 5... for exit ");
            key = scanner.nextInt();
            if (key == 1) {
                Doctor newDoctor = new Doctor();
                d.add(newDoctor);
            }

            if (key == 2) {
                System.out.println("Press 1 for patient registered.");
                System.out.println("Press 2 for patient Records.");
                System.out.println("Press 3 for patient Checkup.");
                System.out.println("Press 4 for patient Prescription.");
                System.out.println("Press 5 for exit from patient.");

                int no = scanner.nextInt();
                if (no == 1) {
                    Scanner news = new Scanner(System.in);
                    System.out.print("Enter Your Name: ");
                    String name = news.nextLine();
                    
                    System.out.print("Enter Your Age: ");
                    int age = news.nextInt();
                    System.out.println("Press 1 for Blood Pressure. ");
                    System.out.println("Press 2 for Blood Sugar. ");
                    System.out.println("Press 3 for Cholesterol. ");

                    int problemNo = news.nextInt();

                    Patient patient = new Patient(name, age, problemNo);
                    
                    allPatients.add(patient);
                    System.out.println(allPatients.get(0));
                }
                
                if(no==2){
                    
                    int selectionNo;
                    Scanner sc=new Scanner(System.in);
                    if(allPatients.size()<1){
                        System.out.println("Please Register patients first..");
                        continue;
                    }
                    System.out.println("Select which user data you want to see..");
                    for(int i =0;i<allPatients.size();i++){
                        if(allPatients.get(i).retrieveMedicalRecord().size()==0){
                        continue;
                    }
                            
                    else{
                        String name = allPatients.get(i).retrieveMedicalRecord().get(0).split(" ")[0];
                        System.out.println("Select "+name+ " with "+i);
                    }
                        
                    }
                    selectionNo = sc.nextInt();
                    System.out.println(allPatients.get(selectionNo).retrieveMedicalRecord());
                }

                if(no==3){
                    Scanner sc = new Scanner(System.in);
                    if(d.size()>0){
                        if(allPatients.size()>0){
                        System.out.println("Enter patient no: ");
                    int patientNo = sc.nextInt();
                    System.out.println("Fetching user details...");
                        int problemNo=allPatients.get(patientNo).checkupType;
                    Checkup c = d.get(0).performCheckup(problemNo);
                    allPatients.get(patientNo).resultsRecords(c);
                    System.out.println(allPatients.get(patientNo).retrieveMedicalRecord());
                    }
                    
                    else{
                        System.out.println("No patient is registered right now..");
                        continue;
                    }
                    }
                    
                    else{
                        System.out.println("Please register doctor for checkup....");
                    }
                    
                    
                }

                if(no==4){
                    Chemist nc= new Chemist();
                    if(allPatients.size()>0 ){
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Select which user data you want to see..");
                        
                    for(int i =0;i<allPatients.size();i++){
                        if(allPatients.get(0).retrieveMedicalRecord().size()<1){
                            System.out.println("No record found for Prescription...");
                            break;
                        }
                        
                        String name = allPatients.get(i).retrieveMedicalRecord().get(0).split(" ")[0];
                        System.out.println("Select "+name+ " with "+i);
                    }
                    
                    
                        int selectionNo = sc.nextInt();
                        nc.providePrescription(allPatients.get(selectionNo));
                        
                    
                    
                    }
                    else{
                        System.out.println("No patient registered yet...");
                        continue;
                    }
                    
                    
                }

            }

        }

    }
}
