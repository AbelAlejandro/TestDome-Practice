package com.testdome;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Song {
    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isRepeatingPlaylist() {
        Map<String, Integer> referenceMap = new HashMap<>();
        Song nextSong = this;
        while(nextSong.nextSong != null) {
            if(referenceMap.getOrDefault(nextSong.name, 0) > 1) return true;
            if(!referenceMap.containsKey(nextSong.name)) {
                referenceMap.put(name, referenceMap.getOrDefault(name, 0) + 1);
            }
            nextSong = nextSong.nextSong;
        }
        return false;
    }
    public boolean isRepeatingPlaylist2() {
        Set<String> referenceSet = new HashSet<>();
        Song nextSong = this;
        while(nextSong.nextSong != null) {
            if(referenceSet.contains(nextSong.name)) return true;
            if(!referenceSet.contains(nextSong.name)) {
                referenceSet.add(name);
            }
            nextSong = nextSong.nextSong;
        }
        return false;
    }

    public boolean isRepeatingPlaylist3() {
        Song tortoise = this;
        Song hare = this;
        // There's only one element in the list and it's not pointing to itself
        if (tortoise.nextSong == null) {
            return false;
        }
        // Keep going while there are elements in list:
        while (hare != null && hare.nextSong != null) {
            // Advance both, but at their own speeds:
            tortoise = tortoise.nextSong;     // 1 hop
            hare = hare.nextSong.nextSong;    // 2 hops
            if (tortoise == hare) {
                // hare can meet the tortoise only in a loop!
                return true;
            }
        }
        // hare found the end of the list, so no cycle:
        return false;
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist3());
    }
}