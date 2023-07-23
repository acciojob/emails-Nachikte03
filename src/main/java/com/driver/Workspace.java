package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar = new ArrayList<>();

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        Collections.sort(calendar,(p,q)->{
            if(p.getStartTime().isAfter(q.getStartTime())){
                return 1;
            }
            else if(p.getStartTime().isBefore(q.getStartTime())){
                return -1;
            }
            else{
                return 0;
            }
        });
        LocalTime prev_start = calendar.get(0).getStartTime();
        LocalTime prev_end = calendar.get(0).getEndTime();
        int count = 1;
        for(int i=1;i<calendar.size();i++){
            LocalTime curr_start = calendar.get(i).getStartTime();
            LocalTime curr_end = calendar.get(i).getEndTime();
            if(curr_start.isAfter(prev_end)){
                prev_end = curr_end;
                count++;
            }
            else{
                if(prev_end.isAfter(curr_end)){
                    prev_end = curr_end;
                }
            }
        }
        return count;
    }
}
