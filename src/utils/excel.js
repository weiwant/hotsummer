import ExcelJS from "exceljs";
import FileSaver from "file-saver";




export function downloadTemplate(columns, filename) {
    const wb = new ExcelJS.Workbook();
    const sheet1 = wb.addWorksheet("sheet1");
    sheet1.columns = columns;
    wb.xlsx.writeBuffer().then((buffer) => {
        let file = new Blob([buffer], {
            type: 'application/octet-stream',
        });
        FileSaver.saveAs(file, filename)
    })
}