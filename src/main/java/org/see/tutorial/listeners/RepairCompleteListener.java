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
import org.see.tutorial.models.interactions.RepairComplete;
import org.see.tutorial.models.objects.LunarRover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepairCompleteListener implements InteractionListener {
    private static final Logger logger = LoggerFactory.getLogger(RepairCompleteListener.class);
    private final LunarRover rover;

    public RepairCompleteListener(LunarRover rover) {
        this.rover = rover;
    }

    @Override
    public void received(Object interaction) {
        // By registering this event listener, the rover federate can monitor for the RepairComplete interaction.
        // Once that event is received, it fires the RepairComplete interaction with the status of the repair.
        // For simplicity's sake, we assume all repair operations were completed without incident.
        if (interaction instanceof RepairComplete) {
            RepairComplete repairComplete = (RepairComplete) interaction;
            logger.info("Received notification of completed repairs for {}!", repairComplete.getReceivingObject());
            rover.repairCompleted();
        }
    }
}
