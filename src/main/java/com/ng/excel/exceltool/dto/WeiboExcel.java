package com.ng.excel.exceltool.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;
import java.util.Date;

public class WeiboExcel extends BaseRowModel implements Serializable{

    @ExcelProperty(value = "微博ID", index = 0)
    private Integer weiboId;

    @ExcelProperty(value = "用户ID", index = 1)
    private Integer userId;

    @ExcelProperty(value = "内容", index = 2)
    private String weiboText;

    @ExcelProperty(value = "创建时间", index = 2)
    private Date createDate;

    public Integer getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Integer weiboId) {
        this.weiboId = weiboId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getWeiboText() {
        return weiboText;
    }

    public void setWeiboText(String weiboText) {
        this.weiboText = weiboText;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

/*import com.alibaba.excel.annotation.ExcelProperty
import com.alibaba.excel.metadata.BaseRowModel

    data class OrderMarketExcel(
    @ExcelProperty(value = "镖书号", index = 0)
    val orderNum: String?,
    @ExcelProperty(value = "调度数量", index = 1)
    val dispatchedVehicleNum: Long?,
    @ExcelProperty(value = "创建人部门", index = 2)
    val createDepartmentName: String?
    *//*@ExcelProperty(value = "发货方", index = 1)
    var shipperName: String? = null,
    @ExcelProperty(value = "发货方联系人", index = 2)
    var shipperContacts: String? = null,
    @ExcelProperty(value = "发货方手机号码", index = 3)
    var shipperMobile: String? = null,
    @ExcelProperty(value = "收货方", index = 4)
    var receiverName: String? = null,
    @ExcelProperty(value = "收货方联系人", index = 5)
    var receiverContacts: String? = null,
    @ExcelProperty(value = "收货方手机号码", index = 6)
    var receiverMobile: String? = null,
    @ExcelProperty(value = "发货地仓库", index = 7)
    var shipperWarehouse: String? = null,
    @ExcelProperty(value = "发货地省份", index = 8)
    var shipperProvince: String? = null,
    @ExcelProperty(value = "发货地城市", index = 9)
    var shipperCity: String? = null,
    @ExcelProperty(value = "发货地区", index = 10)
    var shipperDistrict: String? = null,
    @ExcelProperty(value = "发货地详细地址", index = 11)
    var shipperAddress: String? = null,
    @ExcelProperty(value = "发货地装货联系人", index = 12)
    var shipperLoadingContacts: String? = null,
    @ExcelProperty(value = "发货地装货联系人电话", index = 13)
    var shipperLoadingMobile: String? = null,
    @ExcelProperty(value = "收货地仓库", index = 14)
    var receiverWarehouse: String? = null,
    @ExcelProperty(value = "收货地省份", index = 15)
    var receiverProvince: String? = null,
    @ExcelProperty(value = "收货地城市", index = 16)
    var receiverCity: String? = null,
    @ExcelProperty(value = "收货地区", index = 17)
    var receiverDistrict: String? = null,
    @ExcelProperty(value = "收货地详细地址", index = 18)
    var receiverAddress: String? = null,
    @ExcelProperty(value = "收货地卸货联系人", index = 19)
    var receiverUnloadingContacts: String? = null,
    @ExcelProperty(value = "收货地卸货联系人电话", index = 20)
    var receiverUnloadingMobile: String? = null,
    @ExcelProperty(value = "用车类型", index = 21)
    var vehicleType: String? = null,
    @ExcelProperty(value = "用车长度", index = 22)
    var vehicleLength: String? = null,
    @ExcelProperty(value = "用车数量", index = 23)
    var vehicleNum: Int? = null,
    @ExcelProperty(value = "运费付款方", index = 24)
    var payer: String? = null,
    @ExcelProperty(value = "装货日期", index = 25)
    var loadingDate: LocalDateTime? = null,
    @ExcelProperty(value = "要求到货日期", index = 26)
    var deliveryDate: LocalDateTime? = null,
    @ExcelProperty(value = "用车，装卸货备注", index = 27)
    var vehicleRemark: String? = null,
    @ExcelProperty(value = "业务单据号", index = 28)
    var businessNum: String? = null,
    @ExcelProperty(value = "业务类型", index = 29)
    var businessType: String? = null,
    @ExcelProperty(value = "业务其他备注", index = 30)
    var businessRemark: String? = null,
    @ExcelProperty(value = "是否为一装多卸", index = 31)
    var isMultipleUnloading: Boolean? = null,
    @ExcelProperty(value = "一装多卸信息", index = 32)
    var multipleUnloadingRemark: String? = null,
    @ExcelProperty(value = "状态", index = 33)
    var orderStatus: String? = null,
    @ExcelProperty(value = "锁定状态", index = 34)
    var status: String? = null,
    @ExcelProperty(value = "发镖人", index = 35)
    var createPerson: String? = null,
    @ExcelProperty(value = "发镖时间", index = 36)
    var createTime: LocalDateTime? = null,
    @ExcelProperty(value = "接镖人", index = 37)
    var orderPerson: String? = null,
    @ExcelProperty(value = "接镖时间", index = 38)
    var orderTime: LocalDateTime? = null,
    @ExcelProperty(value = "要求到货日期", index = 39)
    var dispatchTime: LocalDateTime? = null,
    @ExcelProperty(value = "最终到达时间", index = 40)
    var arrivedTime: LocalDateTime? = null,
    @ExcelProperty(value = "撤销备注", index = 41)
    var revokeRemark: String? = null*//*
            ) : BaseRowModel()*/
}
