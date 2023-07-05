package com.server.randommultiple.Listener;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Citizen implements Listener {

    @EventHandler
    public void npcOpenEvent(NPCRightClickEvent event) {
        Player player = event.getClicker();
        NPC npc = (NPC) event.getNPC();
        if (npc.getName().equals("")) {

        }
    }
}
