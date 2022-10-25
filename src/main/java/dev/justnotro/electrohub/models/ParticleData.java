package dev.justnotro.electrohub.models;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParticleData {

    private static Map<UUID, Integer> Trails = new HashMap<>();
    private UUID uuid;

    public ParticleData(UUID uuid) {
        this.uuid = uuid;
    }

    public void spawnParticle(Player player) {

    }

}
