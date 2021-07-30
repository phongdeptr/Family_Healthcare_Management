/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dto.Registration_DTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface RegistrationInterface extends Remote{
    boolean createRegistration(Registration_DTO dto)throws RemoteException;
    Registration_DTO findByRegistrationID(String id)throws RemoteException;
    ArrayList<Registration_DTO> findAllRegistration()throws RemoteException;
    boolean removeRegistration(String id)throws RemoteException;
    boolean updateRegistration(Registration_DTO dto)throws RemoteException; 
}
