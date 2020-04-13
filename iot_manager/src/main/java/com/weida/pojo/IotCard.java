package com.weida.pojo;

import java.util.Date;

public class IotCard {
    private Integer id;

    private String cardid;

    private Integer status;

    private Integer networkstatus;

    private Integer usesize;

    private Integer totalsize;

    private String agentname;

    private String channelname;

    private Integer cardtype;

    private String carddetail;

    private Integer settlementdate;

    private Date addtime;

    private Integer renew;

    private String renewdetail;

    private Integer trafficpool;

    private Integer operator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNetworkstatus() {
        return networkstatus;
    }

    public void setNetworkstatus(Integer networkstatus) {
        this.networkstatus = networkstatus;
    }

    public Integer getUsesize() {
        return usesize;
    }

    public void setUsesize(Integer usesize) {
        this.usesize = usesize;
    }

    public Integer getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(Integer totalsize) {
        this.totalsize = totalsize;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public Integer getCardtype() {
        return cardtype;
    }

    public void setCardtype(Integer cardtype) {
        this.cardtype = cardtype;
    }

    public String getCarddetail() {
        return carddetail;
    }

    public void setCarddetail(String carddetail) {
        this.carddetail = carddetail;
    }

    public Integer getSettlementdate() {
        return settlementdate;
    }

    public void setSettlementdate(Integer settlementdate) {
        this.settlementdate = settlementdate;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getRenew() {
        return renew;
    }

    public void setRenew(Integer renew) {
        this.renew = renew;
    }

    public String getRenewdetail() {
        return renewdetail;
    }

    public void setRenewdetail(String renewdetail) {
        this.renewdetail = renewdetail;
    }

    public Integer getTrafficpool() {
        return trafficpool;
    }

    public void setTrafficpool(Integer trafficpool) {
        this.trafficpool = trafficpool;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }
}