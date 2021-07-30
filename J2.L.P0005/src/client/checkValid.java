/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author ADMIN
 */
public class checkValid {

    private boolean checkRegistrationIDConstraint(String registrationID) {
        boolean checkConstraintResult = false;
        if (registrationID.length() <= 10 && registrationID.matches("([a-zA-Z0-9]{1,})")) {
            return true;
        }
        return checkConstraintResult;
    }

    private boolean checkEmailConstraint(String registrationEmail) {
        boolean checkConstraintResult = false;
        if (registrationEmail.matches("([a-zA-Z]{1,}([0-9]{0,}))([@]){1,1}([a-zA-Z0-9]{1,}).(vn|com$){1,}") && registrationEmail.length() < 31) {
            return true;
        }
        return checkConstraintResult;
    }

    private boolean checkPhoneNumberConstraint(String registrationPhone) {
        boolean checkConstraintResult = false;
        if (registrationPhone.matches("[0-9]{1,}") && registrationPhone.length() < 16) {
            return true;
        }
        return checkConstraintResult;
    }

    private boolean checkNumberOfChildrenConstraint(String registrationNumbberOfChildren) {
        boolean checkConstraintResult = false;
        if (registrationNumbberOfChildren.matches("([0-9]{1,})") && Integer.parseInt(registrationNumbberOfChildren) >= 0) {
            return true;
        }
        return checkConstraintResult;
    }

    private boolean checkNumberOfAdult(String registrationNumberOfAdult) {
        boolean checkConstraintResult = false;
        if(registrationNumberOfAdult.matches("([0-9]{1,})") && Integer.parseInt(registrationNumberOfAdult) >= 0){
            return true;
        }
        return checkConstraintResult;
    }

    private boolean checkFullNameConstraint(String registrationFullName) {
        boolean checkConstraintResult = false;
        if (registrationFullName.length() < 51 && registrationFullName.length() > 0) {
            return true;
        }
        return checkConstraintResult;
    }

    public String validRegistration(String registrationID,
            String registrationFullName,
            String registrationEmail,
            String registrationPhone,
            String registrationNumbberOfChildren,
            String registrationNumberOfAdult) {
        String error = "";
        boolean checkRegistrationIDConstraintResult = checkRegistrationIDConstraint(registrationID);
        boolean checkRegistrationFullNameConstraintResult = checkFullNameConstraint(registrationFullName);
        boolean checkEmailConstraintResult = checkFullNameConstraint(registrationFullName);
        boolean checkPhoneNumberConstraintResult = checkPhoneNumberConstraint(registrationPhone);
        boolean checkNumberOfChildrenConstraintResult = checkNumberOfChildrenConstraint(registrationNumbberOfChildren);
        boolean checkNumberOfAdult = checkNumberOfAdult(registrationNumberOfAdult);
        if (checkRegistrationIDConstraintResult && checkRegistrationFullNameConstraintResult
                && checkEmailConstraintResult && checkPhoneNumberConstraintResult
                && checkNumberOfChildrenConstraintResult && checkNumberOfAdult) {
            return "Success";

        } else {
            if (!checkRegistrationIDConstraintResult) {
                error = error + "- ID must has legnth from 1 to 10 and not contain special character also not is empty\n";
            }
            if (!checkRegistrationFullNameConstraintResult) {
                error = error + "- FullName: max length is 50 and not be empty\n";
            }
            if (!checkEmailConstraintResult) {
                error = error + "- Email maximum length is 30 and contain one character @ and not be empty\n";
            }
            if (!checkPhoneNumberConstraintResult) {
                error = error + "- Phone maximum length is 15 and contain number only && not be empty\n";
            }
            if (!checkNumberOfChildrenConstraintResult) {
                error = error + "- Number of children must >= 0\n";
            }
            if (!checkNumberOfAdult) {
                error = error + "- Number of adult must >= 0\n";
            }
        }
        return error;
    }
}
