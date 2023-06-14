package com.poorva.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailTreeItem<String> extends TreeItem {

    private String name;
    private ObservableList<EmailMessage> emailMessages;
    private int unreadMessagesCount;

    public ObservableList<EmailMessage> getEmailMessages(){
        return emailMessages;
    }
    public EmailTreeItem(String name) {
        super(name);
        this.name = name;
        this.emailMessages = FXCollections.observableArrayList();
    }

    //Method to add java mail messages to simple message of type EmailMessages
    public void addEmail(Message message) throws MessagingException {
        EmailMessage emailMessage = fetchMessage(message);
        emailMessages.add(emailMessage);

    }

    public void addEmailTop(Message message) throws MessagingException {
        EmailMessage emailMessage = fetchMessage(message);
        emailMessages.add(0, emailMessage);
    }

    private EmailMessage fetchMessage(Message message) throws MessagingException {
        boolean messageIsRead = message.getFlags().contains(Flags.Flag.SEEN);
        EmailMessage emailMessage = new EmailMessage(
                message.getSubject(),
                message.getFrom()[0].toString(),
                message.getRecipients(MimeMessage.RecipientType.TO)[0].toString(),
                message.getSize(),
                message.getSentDate(),
                messageIsRead,
                message
        );


        if(!messageIsRead){
            incrementUnreadMessagesCount();
        }
        return emailMessage;
    }



    public void incrementUnreadMessagesCount(){
        unreadMessagesCount++;
        updateName();
    }

    public void decrementUnreadMessagesCount(){
        unreadMessagesCount--;
        updateName();
    }

    private void updateName(){
        if(unreadMessagesCount > 0){
            this.setValue(name + "(" + unreadMessagesCount + ")");
        } else {
            this.setValue(name);

        }
    }


}
