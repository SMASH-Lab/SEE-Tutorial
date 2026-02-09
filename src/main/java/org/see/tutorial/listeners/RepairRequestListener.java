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

package org.see.tutorial.listeners;

import org.see.skf.core.InteractionListener;
import org.see.tutorial.RepairHandler;
import org.see.tutorial.models.interactions.RepairRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepairRequestListener implements InteractionListener {
    private static final Logger logger = LoggerFactory.getLogger(RepairRequestListener.class);

    private final RepairHandler handler;
    public RepairRequestListener(RepairHandler handler) {
        this.handler = handler;
    }

    @Override
    public void received(Object interaction) {
        // The RepairFederate uses this event listener to monitor for repair requests.
        // As soon as one comes in, it is added to the repair handler, which processes these maintenance requests
        // at each time step.
        if (interaction instanceof RepairRequest) {
            RepairRequest repairRequest = (RepairRequest) interaction;
            logger.info("Received a repair request by {} for the {} part type.", repairRequest.getReceivingObject(), repairRequest.getRepairType());

            handler.addRepairRequest(repairRequest);
        }
    }
}
