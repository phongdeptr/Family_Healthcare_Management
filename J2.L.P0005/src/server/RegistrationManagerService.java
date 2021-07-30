/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dto.Registration_DTO;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class RegistrationManagerService extends UnicastRemoteObject implements RegistrationInterface {

    private final String pathStorage = new File("registration.txt").getAbsolutePath();
    // RMI getList<Service>
    private static final ArrayList<Registration_DTO> REGISTRATION_LIST = new ArrayList<>();

    public RegistrationManagerService() throws RemoteException {
        loadRegistrationFromFile();
        System.out.println("this file is fetch from " + new File("registration.txt").getAbsolutePath());
    }

    // Rewrite  to file after remove or update
    void writeToFile() {
        PrintWriter pr;
        try {
            pr = new PrintWriter(new FileOutputStream(pathStorage, false));
            for (int i = 0; i < REGISTRATION_LIST.size(); i++) {
                Registration_DTO get = REGISTRATION_LIST.get(i);
                pr.write(get.toString());
            }
            pr.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File is not exist");
        }
    }

    public final void loadRegistrationFromFile() {
        if (REGISTRATION_LIST.size() > 0) {
            REGISTRATION_LIST.removeAll(REGISTRATION_LIST);
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathStorage));
            while (br.ready()) {
                System.out.println("readLine");
                String[] tmp = br.readLine().split("; ");
                if (tmp.length != 10) {
                    JOptionPane.showMessageDialog(null, "Structure on Line is incorrect");
                    return;
                } else {
                    Registration_DTO registrationItem = new Registration_DTO(tmp[0], tmp[1], new Integer(tmp[2]),
                            Boolean.valueOf(tmp[3]), tmp[4], tmp[5], tmp[6], new Integer(tmp[7]), new Integer(tmp[8]), new Integer(tmp[9]));
                    REGISTRATION_LIST.add(registrationItem);
                    System.out.println(registrationItem.getRegistrationID() + "\t" + registrationItem.getFullName() + "\t" + registrationItem.getAge());
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistrationManagerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationManagerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeadlessException | NumberFormatException e) {

        }
    }

    @Override
    public boolean createRegistration(Registration_DTO dto) throws RemoteException {
        boolean check = false;
        PrintWriter pr = null;
        Registration_DTO result = null;
        if (REGISTRATION_LIST.size() > 0) {
            REGISTRATION_LIST.removeAll(REGISTRATION_LIST);
            result = findByRegistrationID(dto.getRegistrationID());
        }
        if (result != null) {
            JOptionPane.showMessageDialog(null, "DUPLICATE ID");
        } else {
            try {
                pr = new PrintWriter(new FileOutputStream(pathStorage, true));
                pr.append(dto.toString());
                check = true;
                pr.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RegistrationManagerService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    @Override
    public Registration_DTO findByRegistrationID(String id) throws RemoteException {
        Registration_DTO result = null;
        loadRegistrationFromFile();
        for (int i = 0; i < REGISTRATION_LIST.size(); i++) {
            Registration_DTO sample = REGISTRATION_LIST.get(i);
            if (sample.getRegistrationID().equals(id)) {
                result = sample;
                break;
            }
        }
        return result;
    }

    @Override
    public ArrayList<Registration_DTO> findAllRegistration() throws RemoteException {
        loadRegistrationFromFile();
        return REGISTRATION_LIST;
    }

    @Override
    public boolean removeRegistration(String id) throws RemoteException {
        loadRegistrationFromFile();
        boolean checkFlag = false;
        if (!REGISTRATION_LIST.isEmpty()) {
            for (int i = 0; i < REGISTRATION_LIST.size(); i++) {
                Registration_DTO get = REGISTRATION_LIST.get(i);
                if (get.getRegistrationID().equals(id)) {
                    REGISTRATION_LIST.remove(get);
                    writeToFile();
                    checkFlag = true;
                }
            }
        }
        return checkFlag;
    }

    @Override
    public boolean updateRegistration(Registration_DTO dto) throws RemoteException {
        loadRegistrationFromFile();
        boolean check = false;
        if (dto != null && REGISTRATION_LIST.size() > 0) {
            for (int i = 0; i < REGISTRATION_LIST.size(); i++) {
                Registration_DTO get = REGISTRATION_LIST.get(i);
                if (get.getRegistrationID().equals(dto.getRegistrationID())) {
                    get.setFullName(dto.getFullName());
                    get.setAddress(dto.getAddress());
                    get.setGender(dto.getGender());
                    get.setNumberOfAdults(dto.getNumberOfAdults());
                    get.setNumberOfChildren(dto.getNumberOfChildren());
                    get.setNumberOfMember(dto.getNumberOfMember());
                    get.setPhone(dto.getPhone());
                    get.setEmail(dto.getEmail());
                    get.setAge(dto.getAge());
                    writeToFile();
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    public static void main(String[] args) throws RemoteException {
        try {
            RegistrationInterface rI = new RegistrationManagerService();
            System.out.println("Remote Object is: " + rI);
            Runtime r = Runtime.getRuntime();
            // khoi tao rmi container.
            r.exec("rmiregistry.exe");
            // dang ki port voi container
            LocateRegistry.createRegistry(2223);
            // bind object toi url
            Naming.rebind("rmi://localhost:2223/res", rI);
            System.out.println("server started");
        } catch (IOException e) {

        }

    }
}
