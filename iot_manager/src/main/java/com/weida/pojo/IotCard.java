package com.weida.pojo;

import java.util.Date;

public class IotCard {
    private Integer Id;

    private String cardId;

    private Integer status;

    private Integer networkStatus;

    private Integer useSize;

    private Integer totalSize;

    private String agentName;

    private String channelName;

    private Integer cardType;

    private String cardDetail;

    private Integer settlementDate;

    private Date addTime;

    private Integer renew;

    private String renewDetail;

    private Integer traffiCpool;

    private Integer operator;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(Integer networkStatus) {
        this.networkStatus = networkStatus;
    }

    public Integer getUseSize() {
        return useSize;
    }

    public void setUseSize(Integer useSize) {
        this.useSize = useSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardDetail() {
        return cardDetail;
    }

    public void setCardDetail(String cardDetail) {
        this.cardDetail = cardDetail;
    }

    public Integer getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Integer settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getRenew() {
        return renew;
    }

    public void setRenew(Integer renew) {
        this.renew = renew;
    }

    public String getRenewDetail() {
        return renewDetail;
    }

    public void setRenewDetail(String renewDetail) {
        this.renewDetail = renewDetail;
    }

    public Integer getTraffiCpool() {
        return traffiCpool;
    }

    public void setTraffiCpool(Integer traffiCpool) {
        this.traffiCpool = traffiCpool;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }
}