package singlechat.server.message.dao;

import java.io.Serializable;

public class Message implements Serializable {
	/**  */
	private static final long serialVersionUID = 1L;
	private int id;
	private String sender;
	private String receiver;
	private String content;
	private String sendDate;
	private int state;

	public Message() {

	}

	public Message(String sender, String receiver, String content,
			String sendDate) {
		this.content = content;
		this.receiver = receiver;
		this.sender = sender;
		this.sendDate = sendDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	@Override
	public String toString() {
		return content;
	}
}
