package com.poorva.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.mail.Message;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailMessage {
    // We are defining and using object properties as javafx TableView does not support simple objects such as string etc
    // object properties are same but with cool functionalities
    private SimpleStringProperty subject;
    private SimpleStringProperty sender;
    private SimpleStringProperty recipient;
    private SimpleObjectProperty<SizeInteger> size;
    private SimpleObjectProperty<Date> date;
    private boolean isRead;
    private Message message; //reference to the original message

    public List<MimeBodyPart> getAttachmentList() {
        return attachmentList;
    }

    private List<MimeBodyPart> attachmentList = new ArrayList<>();

    public boolean hasAttachment() {
        return hasAttachment;
    }

    private boolean hasAttachment = false;

    public  EmailMessage(String subject, String sender, String recipient, int size, Date date, boolean isRead, Message message){
        this.subject = new SimpleStringProperty(subject);
        this.sender = new SimpleStringProperty(sender);
        this.recipient = new SimpleStringProperty(recipient);
        this.size = new SimpleObjectProperty(new SizeInteger(size));
        this.date = new SimpleObjectProperty<>(date);
        this.isRead = isRead;
        this.message = message;

    }

    public String getSubject(){
        return this.subject.get();
    }

    public String getSender(){
        return this.sender.get();
    }

    public String getRecipient(){
        return this.recipient.get();
    }

    public SizeInteger getSize(){
        return this.size.get();
    }

    public Date getDate(){
        return this.date.get();
    }

    public boolean isRead(){
        return isRead;
    }

    public void setIsRead(boolean read){
        isRead = read;
    }

    public Message getMessage(){
        return this.message;
    }


    public void addAttachment(MimeBodyPart mimeBodyPart) {
        hasAttachment = true;
        attachmentList.add(mimeBodyPart);
    }
}
