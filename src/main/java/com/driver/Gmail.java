package com.driver;

import java.util.*;

public class git Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    Mail front;//indicates latest
    Mail rear;//indicates oldest
    List<Mail> t = new LinkedList<>();
    int curr;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        front = new Mail(null,"-1","-1");
        rear = new Mail(null,"1","1");
        front.next = rear;
        rear.prev = front;
        curr = 0;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        Mail mail = new Mail(date,sender,message);
        Mail nextMail = front.next;
        front.next = mail;
        mail.prev = front;
        mail.next = nextMail;
        nextMail.prev = mail;
        curr++;
        if(curr>inboxCapacity){
            Mail prevMail = rear.prev.prev;
            Mail p = rear.prev;
            if(prevMail!=null){
                prevMail.next = rear;
                rear.prev = prevMail;
            }
            t.add(p);
            curr = inboxCapacity;
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        if(curr==0){
            return;
        }
        Mail dummy = front.next;
        while(dummy.next!=null){
            if(dummy.message.equals(message)){
                Mail prevMail = dummy.prev;
                Mail nextMail = dummy.next;
                prevMail.next = nextMail;
                nextMail.prev = prevMail;
                curr = curr-1;
            }
            dummy = dummy.next;
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(front.next.next!=null){
            return front.next.message;
        }
        else{
            return null;
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(rear.prev.prev!=null){
            return rear.prev.message;
        }
        else{
            return null;
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        Mail dummy = front.next;
        while(dummy.next!=null){
           if(dummy.date.equals(start) || dummy.date.equals(end) || (dummy.date.after(start) && dummy.date.before(end))){
               count++;
           }
           dummy = dummy.next;
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return curr;
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return t.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        t.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
