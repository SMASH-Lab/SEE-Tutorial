/*****************************************************************
 SEE Tutorial - Tutorial projects demonstrating how to use the
 SEE HLA Starter Kit Framework.
 Copyright (c) 2026, Hridyanshu Aatreya - Modelling & Simulation
 Group (MSG) at Brunel University of London. All rights reserved.

 GNU Lesser General Public License (GNU LGPL).

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 3.0 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library.
 If not, see http://http://www.gnu.org/licenses/
 *****************************************************************/

package org.see.tutorial;

import hla.rti1516_2025.exceptions.*;
import org.see.skf.conf.FederateConfiguration;
import org.see.skf.core.SEEFederateAmbassador;
import org.see.skf.core.SEELateJoinerFederate;
import org.see.tutorial.listeners.RepairRequestListener;
import org.see.tutorial.models.interactions.RepairComplete;
import org.see.tutorial.models.interactions.RepairRequest;
import org.see.tutorial.models.interactions.RepairResponse;
import org.see.tutorial.models.objects.PhysicalEntity;

import java.io.File;

public class RepairFederate extends SEELateJoinerFederate {
    private static final File confFile = new File("src/main/resources/conf/repair_federate.conf");
    private final RepairHandler repairHandler;

    public RepairFederate(SEEFederateAmbassador federateAmbassador, FederateConfiguration federateConfiguration) {
        super(federateAmbassador, federateConfiguration);
        repairHandler = new RepairHandler(this);
    }

    @Override
    public void declareClasses() throws FederateNotExecutionMember, AttributeNotDefined, ObjectClassNotDefined, RestoreInProgress, NameNotFound, NotConnected, RTIinternalError, InvalidObjectClassHandle, SaveInProgress, InvalidInteractionClassHandle, InteractionClassNotDefined, FederateServiceInvocationsAreBeingReportedViaMOM {
        // We will publish the repair complete interaction since all repairs are done by this federate.
        publishInteractionClass(RepairComplete.class);

        // The rover is a PhysicalEntity object instance, so we want to subscribe to that class.
        subscribeObjectClass(PhysicalEntity.class);

        // We will be receiving repair requests and response interactions.
        subscribeInteractionClass(RepairRequest.class);
        subscribeInteractionClass(RepairResponse.class);

        // This event listener will listen for repair requests.
        addInteractionListener(new RepairRequestListener(repairHandler));
    }

    @Override
    public void declareObjectInstances() {
        // There are no object instances to be declared: the federate's repair handling system implements that behavior.
    }

    @Override
    public void update() {
        // Process all repair jobs per time step.
        repairHandler.process();
    }

    public static void main(String[] args) {
        FederateConfiguration config = FederateConfiguration.Factory.create(confFile);
        RepairFederate federate = new RepairFederate(new SEEFederateAmbassador(), config);
        federate.configureAndStart();
    }
}

