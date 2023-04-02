package org.example;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Comparator;

public class ManagementPrimarie {

    public static void main(String[] args) throws IOException, ParseException {
        // Your code here
        List<Utilizator> users = new ArrayList<>();//colectie utlizatori
        String fileName = args[0];
        File file = new File("./src/main/resources/input/" + fileName);
        List<Cerere> cereri = new ArrayList<>();
        Birou<Persoana> birouPers = new Birou<>();
        Birou<Pensionar> birouPens = new Birou<>();
        Birou<Elev> birouElevi = new Birou<>();
        Birou<Angajat> birouAng = new Birou<>();
        Birou<EntitateJuridica> birouEj = new Birou<>();
        HashMap <String, String> col = new HashMap<>();
        PriorityQueue<Cerere> queue = new PriorityQueue<>(new Comparator<Cerere>() {
            @Override
            public int compare(Cerere o1, Cerere o2) {
                if (o1.date.getTime() < o2.date.getTime())
                    return -1;
                else if (o1.date.getTime() == o2.date.getTime())
                    return 0;
                else
                    return 1;
            }
        });
        PriorityQueue<Cerere> queueFinalizate = new PriorityQueue<>(new Comparator<Cerere>() {
            @Override
            public int compare(Cerere o1, Cerere o2) {
                if (o1.date.getTime() < o2.date.getTime())
                    return -1;
                else if (o1.date.getTime() == o2.date.getTime())
                    return 0;
                else
                    return 1;
            }
        });
        try {
            Scanner scanner = new Scanner(file);
            String numeUser = null;
            while (scanner.hasNextLine()) {
                String line1 = scanner.nextLine();
                String[] sep = line1.split(";");
                String command = sep[0];
                String sep1 = sep[1].trim();
                //  String sep3 = sep[3].trim();

                if (command.equals("adauga_utilizator")) {
                    String sep2 = sep[2].trim();
                    numeUser = sep2;
                    // System.out.println("yeyyy");
                    //verific tipul de user
                    //String sep3 = sep[3].trim();

                    if (sep1.equals("persoana")) {
                        Persoana pers = new Persoana(sep2);
                        users.add(pers);
                        col.put(sep2, sep1);
                    } else if (sep1.equals("angajat")) {
                        String sep3 = sep[3].trim();
                        Angajat ang = new Angajat(sep2, sep3);
                        users.add(ang);
                        col.put(sep2, sep1);
                        // System.out.println("da");
                    } else if (sep1.equals("pensionar")) {
                        //      String sep3 = sep[3].trim();
                        Pensionar pens = new Pensionar(sep2);
                        users.add(pens);
                        col.put(sep2, sep1);
                    } else if (sep1.equals("elev")) {
                        String sep3 = sep[3].trim();
                        Elev elev = new Elev(sep2, sep3);
                        users.add(elev);
                        col.put(sep2, sep1);
                    } else if (sep1.equals("entitate juridica")) {
                        String sep3 = sep[3].trim();
                        EntitateJuridica ej = new EntitateJuridica(sep2, sep3);
                        users.add(ej);
                        col.put(sep2, sep1);
                    }
                }
                if (command.equals("cerere_noua")) {
                    //System.out.println("da");
                    String sep3 = sep[3].trim();
                    String tip = sep[2].trim();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                    String dateString = sep3.trim();
                    Date date = sdf.parse(dateString);
                    String sep4 = sep[4].trim();
                    for (Utilizator user : users) {
                        if (user instanceof Persoana) {
                            Persoana t = (Persoana) user;
                            if (t.getName().equals(sep1.trim())) {
                                Cerere cerere = t.createCerere(Cerere.Type.getTypeByParam(tip), Integer.parseInt(sep4), date);
                                if (cerere == null) {
                                    appendStrToFile("./src/main/resources/output/" + fileName, "Utilizatorul de tip persoana nu poate inainta o cerere de tip " + tip + "\n");
                                } else {
                                    queue.add(cerere);
                                    birouPers.addRequest(cerere);
                                }
                                //System.out.println(cerere);
                                // cereri.add(cerere);
                                break;
                            }
                        } else if (user instanceof Pensionar) {
                            Pensionar s = (Pensionar) user;
                            if (s.getName().equals(sep1.trim())) {
                                Cerere cerere = s.createCerere(Cerere.Type.getTypeByParam(tip), Integer.parseInt(sep4), date);
                                if (cerere == null) {
                                    appendStrToFile("./src/main/resources/output/" + fileName, "Utilizatorul de tip pensionar nu poate inainta o cerere de tip " + tip + "\n");
                                } else {
                                    queue.add(cerere);
                                    birouPens.addRequest(cerere);
                                }
                                break;
                            }
                        } else if (user instanceof Angajat) {
                            Angajat a = (Angajat) user;
                            if (a.getName().equals(sep1.trim())) {
                                Cerere cerere = a.createCerere(Cerere.Type.getTypeByParam(tip), Integer.parseInt(sep4), date);
                                if (cerere == null) {
                                    appendStrToFile("./src/main/resources/output/" + fileName, "Utilizatorul de tip angajat nu poate inainta o cerere de tip " + tip + "\n");
                                } else {
                                    queue.add(cerere);
                                    birouAng.addRequest(cerere);
                                }
                                break;
                            }
                        } else if (user instanceof Elev) {
                            Elev el = (Elev) user;
                            if (el.getName().equals(sep1.trim())) {
                                Cerere cerere = el.createCerere(Cerere.Type.getTypeByParam(tip), Integer.parseInt(sep4), date);
                                if (cerere == null) {
                                    appendStrToFile("./src/main/resources/output/" + fileName, "Utilizatorul de tip elev nu poate inainta o cerere de tip " + tip + "\n");
                                } else {
                                    queue.add(cerere);
                                    birouElevi.addRequest(cerere);
                                }
                                break;
                            }
                        } else if (user instanceof EntitateJuridica) {
                            EntitateJuridica ej = (EntitateJuridica) user;
                            if (ej.getNumeCompanie().equals(sep1.trim())) {
                                Cerere cerere = ej.createCerere(Cerere.Type.getTypeByParam(tip), Integer.parseInt(sep4), date);
                                if (cerere == null) {
                                    appendStrToFile("./src/main/resources/output/" + fileName, "Utilizatorul de tip entitate juridica nu poate inainta o cerere de tip " + tip + "\n");
                                } else {
                                    queue.add(cerere);
                                    birouEj.addRequest(cerere);
                                }
                                break;
                            }
                        }
                    }//user is found


                }
                if (command.equals("afiseaza_cereri_in_asteptare")) {
                    //System.out.println("aaa");
                    PriorityQueue<Cerere> shallow = new PriorityQueue<>(queue);
                    appendStrToFile("./src/main/resources/output/" + fileName, sep1 + " - cereri in asteptare:\n");
                    while (!shallow.isEmpty()) {
                        //System.out.println(r);
                        String s1 = sep1 + ",";
                        Cerere c = shallow.poll();
                        String[] arr = c.toString().split(" ");
                        String comp = arr[10] + " " + arr[11];
                        String nume = arr[4] + " " + arr[5];
                        if (s1.equals(nume) || s1.equals(comp)) {
                            appendStrToFile("./src/main/resources/output/" + fileName, c.toString());
                        }
                    }
                }
                if (command.equals("retrage_cerere")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                    String dateString = sep[2].trim();
                    Date date = sdf.parse(dateString);
                    PriorityQueue<Cerere> sh = new PriorityQueue<>(queue);
                   /* for(Cerere r:sh){
                        // System.out.println(c);
                        if(r.date.equals(date)){
                            // System.out.println(sh.poll());
                            queue.remove(r);
                        }
                    }*/
                    while (!sh.isEmpty()) {
                        Cerere r = sh.poll();
                        String[] arr = r.toString().split(" ");
                        String tip = col.get(sep1);
                        System.out.println(tip);
                        if (r.date.equals(date)){
                            queue.remove(r);
                            if (tip.equals("persoana")) {
                                birouPers.getCoadaCereri().remove(r);
                            }
                            if (tip.equals("pensionar")) {
                                birouPens.getCoadaCereri().remove(r);
                            }
                            if (tip.equals("angajat")) {
                                birouAng.getCoadaCereri().remove(r);
                            }
                            if (tip.equals("elev")) {
                                birouElevi.getCoadaCereri().remove(r);
                            }
                            if (tip.equals("entitate juridica")) {
                                birouEj.getCoadaCereri().remove(r);
                            }}

                    }
                }
                if (command.equals("afiseaza_cereri")) {
                    appendStrToFile("./src/main/resources/output/" + fileName, sep1 + " - cereri in birou:\n");
                    if (sep1.equals("persoana")) {
                        PriorityQueue<Cerere> shallow = new PriorityQueue<>(birouPers.getCoadaCereri());
                        while (!shallow.isEmpty()) {
                            Cerere c = shallow.poll();
                            appendStrToFile("./src/main/resources/output/" + fileName, c.priority + " - " + c);
                        }

                    }
                    if (sep1.equals("pensionar")) {
                        PriorityQueue<Cerere> shallow = new PriorityQueue<>(birouPens.getCoadaCereri());
                        while (!shallow.isEmpty()) {
                            Cerere c = shallow.poll();
                            appendStrToFile("./src/main/resources/output/" + fileName, c.priority + " - " + c);
                        }
                    }
                    if (sep1.equals("angajat")) {
                        PriorityQueue<Cerere> shallow = new PriorityQueue<>(birouAng.getCoadaCereri());
                        while (!shallow.isEmpty()) {
                            Cerere c = shallow.poll();
                            appendStrToFile("./src/main/resources/output/" + fileName, c.priority + " - " + c);
                        }
                    }
                    if (sep1.equals("elev")) {
                        PriorityQueue<Cerere> shallow = new PriorityQueue<>(birouElevi.getCoadaCereri());
                        while (!shallow.isEmpty()) {
                            Cerere c = shallow.poll();
                            appendStrToFile("./src/main/resources/output/" + fileName, c.priority + " - " + c);
                        }
                    }
                    if (sep1.equals("entitate juridica")) {
                        PriorityQueue<Cerere> shallow = new PriorityQueue<>(birouEj.getCoadaCereri());
                        while (!shallow.isEmpty()) {
                            Cerere c = shallow.poll();
                            appendStrToFile("./src/main/resources/output/" + fileName, c.priority + " - " + c);
                        }
                    }
                }
                if (command.equals("adauga_functionar")) {
                    String numeF = sep[2].trim();
                    if (sep1.equals("persoana")) {
                        FunctionarPublic f = new FunctionarPublic(numeF);
                        birouPers.addFunctionar(f);
                    }
                    if (sep1.equals("pensionar")) {
                        FunctionarPublic f = new FunctionarPublic(numeF);
                        birouPens.addFunctionar(f);
                    }
                    if (sep1.equals("angajat")) {
                        FunctionarPublic f = new FunctionarPublic(numeF);
                        birouAng.addFunctionar(f);
                    }
                    if (sep1.equals("elev")) {
                        FunctionarPublic f = new FunctionarPublic(numeF);
                        birouElevi.addFunctionar(f);
                    }
                    if (sep1.equals("entitate juridica")) {
                        FunctionarPublic f = new FunctionarPublic(numeF);
                        birouEj.addFunctionar(f);
                    }
                }
                if (command.equals("rezolva_cerere")) {
                    //in ce birou suntem?
                    String numeF = sep[2].trim();
                    if (sep1.equals("persoana")) {
                        for (FunctionarPublic f : birouPers.getListaFunct()) {
                            if (f.getNume().equals(numeF)) {
                                Cerere c = birouPers.getCoadaCereri().poll();
                                queue.remove(c);
                                queueFinalizate.add(c);
                                String fisier = "./src/main/resources/output/" + "functionar_" + f.getNume() + ".txt";
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                                String date = simpleDateFormat.format(c.getDate());
                                String[] arr = c.toString().split(" ");
                                String nume = arr[4] + " " + arr[5];
                                String n = nume.replace(",", "");
                                String text = date + " - " + n;
                                appendStrToFile(fisier, text+"\n");
                            }
                        }
                    }
                    if (sep1.equals("pensionar")) {
                        for (FunctionarPublic f : birouPens.getListaFunct()) {
                            if (f.getNume().equals(numeF)) {
                                Cerere c = birouPens.getCoadaCereri().poll();
                                queue.remove(c);
                                queueFinalizate.add(c);
                                String fisier = "./src/main/resources/output/" + "functionar_" + f.getNume() + ".txt";
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                                String date = simpleDateFormat.format(c.getDate());
                                String[] arr = c.toString().split(" ");
                                String nume = arr[4] + " " + arr[5];
                                String n = nume.replace(",", "");
                                String text = date + " - " + n;
                                appendStrToFile(fisier, text+"\n");
                            }
                        }
                    }
                    if (sep1.equals("angajat")) {
                        for (FunctionarPublic f : birouAng.getListaFunct()) {
                            if (f.getNume().equals(numeF)) {
                                Cerere c = birouAng.getCoadaCereri().poll();
                                queue.remove(c);
                                queueFinalizate.add(c);
                                String fisier = "./src/main/resources/output/" + "functionar_" + f.getNume() + ".txt";
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                                String date = simpleDateFormat.format(c.getDate());
                                String[] arr = c.toString().split(" ");
                                String nume = arr[4] + " " + arr[5];
                                String n = nume.replace(",", "");
                                String text = date + " - " + n;
                                appendStrToFile(fisier, text+"\n");
                            }
                        }
                    }
                    if (sep1.equals("elev")) {
                        for (FunctionarPublic f : birouElevi.getListaFunct()) {
                            if (f.getNume().equals(numeF)) {
                                Cerere c = birouElevi.getCoadaCereri().poll();
                                queue.remove(c);
                                queueFinalizate.add(c);
                                String fisier = "./src/main/resources/output/" + "functionar_" + f.getNume() + ".txt";
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                                String date = simpleDateFormat.format(c.getDate());
                                String[] arr = c.toString().split(" ");
                                String nume = arr[4] + " " + arr[5];
                                String n = nume.replace(",", "");
                                String text = date + " - " + n;
                                appendStrToFile(fisier, text+"\n");
                            }
                        }
                    }
                    if (sep1.equals("entitate juridica")) {
                        for (FunctionarPublic f : birouEj.getListaFunct()) {
                            if (f.getNume().equals(numeF)) {
                                Cerere c = birouEj.getCoadaCereri().poll();
                                queue.remove(c);
                                queueFinalizate.add(c);
                                String fisier = "./src/main/resources/output/" + "functionar_" + f.getNume() + ".txt";
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                                String date = simpleDateFormat.format(c.getDate());
                                String[] arr = c.toString().split(" ");
                                String nume = arr[10] + " " + arr[11];
                                String n = nume.replace(",", "");
                                String text = date + " - " + n;
                                appendStrToFile(fisier, text+"\n");
                            }
                        }
                    }
                }
                if (command.equals("afiseaza_cereri_finalizate")) {
                    PriorityQueue<Cerere> shallow = new PriorityQueue<>(queueFinalizate);
                    appendStrToFile("./src/main/resources/output/" + fileName, sep1 + " - cereri in finalizate:\n");
                    while (!shallow.isEmpty()) {
                        //System.out.println(r);
                        String s1 = sep1 + ",";
                        Cerere c = shallow.poll();
                        String[] arr = c.toString().split(" ");
                        String comp = arr[10] + " " + arr[11];
                        String nume = arr[4] + " " + arr[5];
                        if (s1.equals(nume) || s1.equals(comp)) {
                            appendStrToFile("./src/main/resources/output/" + fileName, c.toString());
                        }
                    }

                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("exception occurred" + e);
        }


       /* System.out.println(Cerere.Type.inrVenitSalarial.getTxt());
        String pattern = "dd-MMM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
        Cerere cerere  = new Cerere(Cerere.Type.inrVenitSalarial, date, 5 );
        System.out.println(cerere.date);*/
        /*Persoana pers = new Persoana("Ion Popescu");
        try {
            System.out.println(pers.scriereText(Cerere.Type.inlocBul));
        } catch (IllegalRequestException e) {
            System.out.print(e.getMessage());
        }*/
       /* String pattern = "dd-MMM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        //System.out.println(date);
        Cerere cerere  = null;
        try {
            cerere = new Cerere(pers.scriereText(Cerere.Type.inlocBul),date, 5 );
        } catch (IllegalRequestException e) {
            throw new RuntimeException(e);
        }
        System.out.println(cerere);*/
    }

    public static void appendStrToFile(String fileName,
                                       String str) {
        // Try block to check for exceptions
        try {

            // Open given file in append mode by creating an
            // object of BufferedWriter class
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));

            // Writing on output stream
            out.write(str);

            // Closing the connection
            out.close();
        } catch (IOException e) {
            System.out.println("upss");
        }
    }
}