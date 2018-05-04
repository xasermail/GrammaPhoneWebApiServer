package hello;

import java.sql.Date;

public class Message {

  private Integer msg_id;
  private Integer to_id;
  private Integer from_id;
  private Date date_create;
  private Date date_update;

  public Message(Integer msg_id, Integer to_id, Integer from_id,
                 Date date_create, Date date_update)
  {
    this.msg_id = msg_id;
    this.to_id = to_id;
    this.from_id = from_id;
    this.date_create = date_create;
    this.date_update = date_update;
  }

  public Integer getMsg_id() {
      return msg_id;
  }

  public Integer getTo_id() {
      return to_id;
  }

  public Integer getFrom_id() {
      return from_id;
  }

  public Date getDate_create() {
      return date_create;
  }

  public Date getDate_update() {
    return date_update;
  }

}