package org.alukyanau.script.ec2;

import com.amazonaws.services.ec2.model.AssociateIamInstanceProfileRequest;
import com.amazonaws.services.ec2.model.GetInstanceUefiDataRequest;
import com.amazonaws.services.ec2.model.GetInstanceUefiDataResult;
import com.amazonaws.services.ec2.model.IamInstanceProfileSpecification;
import com.amazonaws.services.identitymanagement.model.*;
import org.alukyanau.client.AWSClients;
import org.alukyanau.script.Script;

public class InstanceScript implements Script {
    public static void main(String[] args) {
        InstanceScript script = new InstanceScript();
        script.execute();
    }

    @Override
    public void execute() {
//        GetRoleRequest getRoleRequest = new GetRoleRequest()
//                .withRoleName("ReadAccessRoleS3");
//        GetRoleResult roleResult = AWSClients.IAM.getRole(getRoleRequest);
//        Role role = roleResult.getRole();
//
//        GetInstanceProfileRequest getProfileRequest = new GetInstanceProfileRequest()
//                .withInstanceProfileName("ReadAccessRoleS3");
//
//        GetInstanceProfileResult instanceProfileResult = AWSClients.IAM.getInstanceProfile(getProfileRequest);
//        InstanceProfile instanceProfile = instanceProfileResult.getInstanceProfile();

        IamInstanceProfileSpecification profileSpecification = new IamInstanceProfileSpecification()
                .withArn("arn:aws:iam::022222031558:instance-profile/ReadAccessRoleS3");

        AssociateIamInstanceProfileRequest setProfileRequest = new AssociateIamInstanceProfileRequest()
                .withIamInstanceProfile(profileSpecification)
                .withInstanceId("i-0b4ff32d38890d503");

        AWSClients.EC2.associateIamInstanceProfile(setProfileRequest);
        System.out.println("done");
    }
}
