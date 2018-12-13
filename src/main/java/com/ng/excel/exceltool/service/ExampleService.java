package com.ng.excel.exceltool.service;

public class ExampleService {

    /*fun orderMarketDownload(request: OrderPageRequest, response: HttpServletResponse) {
        // 只查询自己部门的数据
        if (currentUser().department!!.type!! == Constants.DepartmentType.BUSINESS.code) {
            request.departmentId = currentUser().departmentId
        } else if (currentUser().department!!.type!! == Constants.DepartmentType.TRANSPORT.code) {
            request.orderDepartmentId = currentUser().departmentId
        }
        // 先根据调度单号查询出镖书号
        if (request.dispatchNum != null && request.dispatchNum.isNotEmpty()) {
            val dispatch = dispatchRepository.findByDispatchNumOrPublishNum(request.dispatchNum)
            // 查询不到调度单直接返回空
            if (dispatch == null) {
                return excelService.writeDataWithTemplate(
                        excelService.marketDownLoadTmpPath,
                        null,
                        null,
                        response.outputStream
                )
            } else {
                request.orderNum = dispatch.orderNum
            }
        }

        // 查询镖书信息
        val orderList = orderMapper.orderMarketDownloadQuery(request)
        if (CollectionUtils.isEmpty(orderList)) {
            return excelService.writeDataWithTemplate(
                    excelService.marketDownLoadTmpPath,
                    null,
                    null,
                    response.outputStream
            )
        }

        val orderIdList = orderList.map { it.id!! }
        // 查询货物信息
        val orderIdToCargoMap = cargoQrueyByOrderIdList(orderIdList)
        // 查询调度信息
        val orderIdToDispatchMap = dispatchService.dispatchQueryByOrderIdList(orderIdList)

        val cellExcelList = mutableListOf<CellExcel>()
        var rowNum = 2
        for (order in orderList) {
            val cargoListTmp = orderIdToCargoMap.get(order.id)
            val dispatchListTmp = orderIdToDispatchMap.get(order.id)
            var rowSizeTmp = 0
            if (dispatchListTmp == null || cargoListTmp!!.size > dispatchListTmp.size) {
                rowSizeTmp = cargoListTmp!!.size
            } else {
                rowSizeTmp = dispatchListTmp.size
            }
            if(rowSizeTmp == 1){
                rowSizeTmp = -1
            }
            var colNum = 0
            // 镖书基本信息
            cellExcelList.add(CellExcel(row = rowNum, column = colNum, data = order.orderNum!!, rowSize = rowSizeTmp))
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = translateOrderStatus(order.orderStatus!!),
            rowSize = rowSizeTmp
                )
            )
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = order.createTime!!,
                    rowSize = rowSizeTmp
                )
            )
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = order.createPerson!!,
                    rowSize = rowSizeTmp
                )
            )
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = order.createDepartmentName!!,
                    rowSize = rowSizeTmp
                )
            )
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = StringUtils.defaultIfEmpty(order.orderPerson,""),
                            rowSize = rowSizeTmp
                    )
            )
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = StringUtils.defaultIfEmpty(order.orderDepartmentName,""),
                            rowSize = rowSizeTmp
                    )
            )
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = order.shipperName!!,
                    rowSize = rowSizeTmp
                )
            )
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = order.shipperProvince!! + order.shipperCity!!,
                    rowSize = rowSizeTmp
                )
            )
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = order.receiverName!!,
                    rowSize = rowSizeTmp
                )
            )
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = order.receiverProvince!! + order.receiverCity!!,
                    rowSize = rowSizeTmp
                )
            )
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = translatePayer(order.payer!!),
            rowSize = rowSizeTmp
                )
            )
            cellExcelList.add(
                    CellExcel(
                            row = rowNum,
                            column = ++colNum,
                            data = StringUtils.defaultIfEmpty(order.businessNum,""),
                            rowSize = rowSizeTmp
                    )
            )

            // 货物信息
            for ((index, cargo) in cargoListTmp.withIndex()) {
                var cargoColNum = colNum
                cellExcelList.add(CellExcel(row = rowNum + index, column = ++cargoColNum, data = cargo.name!!))
                cellExcelList.add(CellExcel(row = rowNum + index, column = ++cargoColNum, data = StringUtils.defaultIfEmpty(cargo.specification,"")))
                cellExcelList.add(CellExcel(row = rowNum + index, column = ++cargoColNum, data = StringUtils.defaultIfEmpty(cargo.piece,"")))
                cellExcelList.add(CellExcel(row = rowNum + index, column = ++cargoColNum, data = if(cargo.weight == null) "" else cargo.weight!!))
                cellExcelList.add(CellExcel(row = rowNum + index, column = ++cargoColNum, data = if(cargo.volume == null ) "" else cargo.volume!!))
                cellExcelList.add(
                        CellExcel(
                                row = rowNum + index,
                                column = ++cargoColNum,
                                data = translateCargoType(cargo.type!!)
                    )
                )
            }

            // 调度信息
            if (dispatchListTmp != null && dispatchListTmp.size > 0) {
                for ((index, dispatch) in dispatchListTmp.withIndex()) {
                    var dispatchColNum = colNum + 6
                    cellExcelList.add(
                            CellExcel(
                                    row = rowNum + index,
                                    column = ++dispatchColNum,
                                    data = dispatch.dispatchNum!!
                        )
                    )
                    cellExcelList.add(
                            CellExcel(
                                    row = rowNum + index,
                                    column = ++dispatchColNum,
                                    data = translateDispatchType(dispatch.type!!)
                        )
                    )
                    cellExcelList.add(
                            CellExcel(
                                    row = rowNum + index,
                                    column = ++dispatchColNum,
                                    data = dispatch.createPerson!!
                        )
                    )
                    cellExcelList.add(
                            CellExcel(
                                    row = rowNum + index,
                                    column = ++dispatchColNum,
                                    data = StringUtils.defaultIfEmpty(dispatch.vehicleNum, "")
                            )
                    )
                    cellExcelList.add(
                            CellExcel(
                                    row = rowNum + index,
                                    column = ++dispatchColNum,
                                    data = StringUtils.defaultIfEmpty(dispatch.driverName, "")
                            )
                    )
                    cellExcelList.add(
                            CellExcel(
                                    row = rowNum + index,
                                    column = ++dispatchColNum,
                                    data = StringUtils.defaultIfEmpty(dispatch.driverMobile, "")
                            )
                    )
                    cellExcelList.add(
                            CellExcel(
                                    row = rowNum + index,
                                    column = ++dispatchColNum,
                                    data = StringUtils.defaultIfEmpty(dispatch.payee,"")
                            )
                    )
                    cellExcelList.add(
                            CellExcel(
                                    row = rowNum + index,
                                    column = ++dispatchColNum,
                                    data = StringUtils.defaultIfEmpty(dispatch.payeeMobile,"")
                            )
                    )
                    cellExcelList.add(
                            CellExcel(
                                    row = rowNum + index,
                                    column = ++dispatchColNum,
                                    data = translateChargeMode(dispatch.chargeMode!!)
                        )
                    )
                    cellExcelList.add(
                            CellExcel(
                                    row = rowNum + index,
                                    column = ++dispatchColNum,
                                    data = if (dispatch.preTaxPrice == null) "" else dispatch.preTaxPrice!!
                        )
                    )
                    cellExcelList.add(
                            CellExcel(
                                    row = rowNum + index,
                                    column = ++dispatchColNum,
                                    data = if (dispatch.preTaxFreight == null) "" else dispatch.preTaxFreight!!
                        )
                    )
                }
            }
            rowNum = rowNum.plus(if (rowSizeTmp == -1) 1 else rowSizeTmp)
        }
        excelService.writeDataWithTemplate(
                excelService.marketDownLoadTmpPath,
                cellExcelList,
                mutableListOf(0),
                response.outputStream
        )
    }*/
}
