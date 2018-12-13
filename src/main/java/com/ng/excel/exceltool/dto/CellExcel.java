package com.ng.excel.exceltool.dto;

import lombok.Data;

@Data
public class CellExcel {
    private int sheetNo;

    private int row;

    private int column;

    private Object data;

    /*
     * 行大小，用于合并单元格，-1表示不用合并
     * */
    private int rowSize;

    /*
     * 列大小，用于合并单元格，-1表示不用合并
     * */
    private int columnSize;
}
