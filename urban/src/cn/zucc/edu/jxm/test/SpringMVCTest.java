package cn.zucc.edu.jxm.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;







import cn.zucc.edu.jxm.entities.ProjectInfo;
import cn.zucc.edu.jxm.entities.Users;
import cn.zucc.edu.jxm.service.ProjectInfoService;
@Controller
public class SpringMVCTest
{
//	@Autowired
//	ProjectInfoService projectInfoService=new ProjectInfoService();
	
    @ResponseBody
    @RequestMapping("/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body)
    {
        System.out.println(body);
        return "helloworld! " + new Date();
    }

    @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException
    {
        byte[] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/files/templet.xls");
        body = new byte[in.available()];
        in.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=templet.xls");

        HttpStatus statusCode = HttpStatus.OK;

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
    }

    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file) throws IOException
    {
        System.out.println("desc: " + desc);
        System.out.println("OriginalFilename: " + file.getOriginalFilename());
        System.out.println("InputStream: " + file.getInputStream());
        System.out.println("Byte :" + file.getBytes().toString());
        return "success";
    }
    @RequestMapping(value = "/excleImport", method = RequestMethod.POST)
    public String excleImport(HttpServletRequest request,@RequestParam("file") File file) throws IOException, Exception
    {
    	System.out.println(file.getName());
    	POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(file));
    	HSSFWorkbook wb=new HSSFWorkbook(fs);
    	HSSFSheet hssfSheet=wb.getSheetAt(0);  // ��ȡ��һ��Sheetҳ
    	if(hssfSheet!=null){
    		System.out.println("������");
    	}
    	return "success";
    } 
    /*private File file;
    
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}*/

	@RequestMapping(value="/importOCRExcelData222",method = RequestMethod.POST)
	public void importOCRExcelData(HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException, InvalidFormatException {
		System.out.println(request);
//		POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(file));
//		System.out.println(fs);
		
//    	HSSFWorkbook wb=new HSSFWorkbook(fs);
//    	System.out.println(file.getName());
		MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = mulRequest.getFile("file");
		String filename = file.getOriginalFilename();
		System.out.println(filename);
	
		if (filename == null || "".equals(filename)) {
		}
//		try {
			/*POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(filename));
	    	HSSFWorkbook wb=new HSSFWorkbook(fs);
	    	HSSFSheet hssfSheet=wb.getSheetAt(0);  // ��ȡ��һ��Sheetҳ
	    	if(hssfSheet!=null){
	    		System.out.println("������");
	    	}*/
			Workbook workbook = WorkbookFactory.create(file.getInputStream());  
			/*List<String> sfList = exportListFromExcel(workbook, 0);
			for (int i = 0; i < sfList.size(); i++) {
				System.out.println("��"+sfList.get(i).toString()+"�� ");
			}*/
			Sheet hssfSheet = workbook.getSheetAt(0);  //ʾ�����sheet  
			if(hssfSheet!=null){
				for(int rowNum=1;rowNum<=hssfSheet.getLastRowNum();rowNum++){
					Row Row=hssfSheet.getRow(rowNum);
					if(Row==null){
						continue;
					}
					ProjectInfo projectInfo =new ProjectInfo();
//					projectInfo.setpId(Integer.valueOf(formatCell(Row.getCell(0))));
//					projectInfo.getEnterpriseInfo().seteId(Integer.valueOf(formatCell(Row.getCell(1))));
					projectInfo.setProjectName(formatCell(Row.getCell(2)));
					
//					projectInfoService.save(projectInfo);
//					Users user=new Users();
//					user.setUserName(ExcelUtil.formatCell(Row.getCell(0)));
				}
	    	}
//			InputStream input = file.getInputStream();
//			System.out.println(file.getInputStream());
//			
	    	
//			XSSFWorkbook workBook = new XSSFWorkbook(input);
//			System.out.println("2"+file.getInputStream());
//			
//			System.out.println(workBook+" input: "+ input);
//			List<String> sfList = exportListFromExcel(workBook, 0);
//			for (int i = 0; i < sfList.size(); i++) {
//				System.out.println("��"+sfList.get(i).toString()+"�� ");
				// LOGGER.info("��"+sfList.get(i).toString()+"�� ");
//			}

		/*} catch (Exception e) {
//			 LOGGER.error(e);
		}*/

	}
	
	public static String formatCell(Cell cell){
		 String result = null;
		if(cell==null){
			return "";
		}else{
			if(cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
				return String.valueOf(cell.getBooleanCellValue());
			}else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
				 if (HSSFDateUtil.isCellDateFormatted(cell))
                 {   
                     // �������ڸ�ʽ��ʱ���ʽ
                     SimpleDateFormat sdf = null;
                     if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm"))
                     {
                         sdf = new SimpleDateFormat("HH:mm");
                     }
                     else if(cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm:ss"))
                     {
                         sdf = new SimpleDateFormat("HH:mm:ss");
                     }
                     else
                     {
                         sdf = new SimpleDateFormat("yyyy-MM-dd");
                     }
                     Date date = cell.getDateCellValue();
                     result = sdf.format(date);
                 }
                 else if (cell.getCellStyle().getDataFormat() == 58)
                 {
                     // �����Զ������ڸ�ʽ��m��d��(ͨ���жϵ�Ԫ��ĸ�ʽid�����id��ֵ��58)
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     double value = cell.getNumericCellValue();
                     Date date = org.apache.poi.ss.usermodel.DateUtil
                             .getJavaDate(value);
                     result = sdf.format(date);
                 }
                 else
                 {
                     double value = cell.getNumericCellValue();
                     CellStyle style = cell.getCellStyle();
                     DecimalFormat format = new DecimalFormat();
                     String temp = style.getDataFormatString();
                     // ��Ԫ�����óɳ���
                     if (temp.equals("General"))
                     {
                        // format.applyPattern("#");
                     }
                     result = format.format(value);
                 }
				return result;
			}else{
				return String.valueOf(cell.getStringCellValue());
			}
		}
	}
	
	/**
     * ��ָ����Sheet������List
     * 
     * @param workbook
     * @param sheetNum
     * @return
     * @throws IOException
     */
    public static List<String> exportListFromExcel(Workbook workbook,
            int sheetNum)
    {

        Sheet sheet = workbook.getSheetAt(sheetNum);

        // ������ʽ���
        FormulaEvaluator evaluator = workbook.getCreationHelper()
                .createFormulaEvaluator();

        List<String> list = new ArrayList<String>();

        int minRowIx = sheet.getFirstRowNum();
        int maxRowIx = sheet.getLastRowNum();
        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++)
        {
            Row row = sheet.getRow(rowIx);
            StringBuilder sb = new StringBuilder();

            short minColIx = row.getFirstCellNum();
            short maxColIx = row.getLastCellNum();
            for (short colIx = minColIx; colIx <= maxColIx; colIx++)
            {
                Cell cell = row.getCell(new Integer(colIx));
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue == null)
                {
                    continue;
                }
                Object result = null;
                switch (cell.getCellType())
                {
                   case Cell.CELL_TYPE_BOOLEAN:
                       result = cell.getBooleanCellValue();
                    break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if (HSSFDateUtil.isCellDateFormatted(cell))
                        {   
                            // �������ڸ�ʽ��ʱ���ʽ
                            SimpleDateFormat sdf = null;
                            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm"))
                            {
                                sdf = new SimpleDateFormat("HH:mm");
                            }
                            else if(cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm:ss"))
                            {
                                sdf = new SimpleDateFormat("HH:mm:ss");
                            }
                            else
                            {
                                sdf = new SimpleDateFormat("yyyy-MM-dd");
                            }
                            Date date = cell.getDateCellValue();
                            result = sdf.format(date);
                        }
                        else if (cell.getCellStyle().getDataFormat() == 58)
                        {
                            // �����Զ������ڸ�ʽ��m��d��(ͨ���жϵ�Ԫ��ĸ�ʽid�����id��ֵ��58)
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            double value = cell.getNumericCellValue();
                            Date date = org.apache.poi.ss.usermodel.DateUtil
                                    .getJavaDate(value);
                            result = sdf.format(date);
                        }
                        else
                        {
                            double value = cell.getNumericCellValue();
                            CellStyle style = cell.getCellStyle();
                            DecimalFormat format = new DecimalFormat();
                            String temp = style.getDataFormatString();
                            // ��Ԫ�����óɳ���
                            if (temp.equals("General"))
                            {
                               // format.applyPattern("#");
                            }
                            result = format.format(value);
                        }
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        {
                        	result = cell.getRichStringCellValue().toString();
                        	list.add(result.toString());
                        }
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        break;
                    default:
                        break;
                }
//                LOGGER.info("��" + result + "�� ");
            }
            list.add(sb.toString());
        }
        return list;
    }


    /*@RequestMapping("/excleImport")
    public void excleImport(HttpServletRequest request) throws IOException, Exception
    {

        request.setCharacterEncoding("utf-8"); // ���ñ���

        // ��ô����ļ���Ŀ����
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // ��ȡ�ļ���Ҫ�ϴ�����·��
        String path = request.getRealPath("/upload");

        File uploadDir = new File(path);
        if (!uploadDir.exists())
        {
            uploadDir.mkdirs();
        }
        factory.setRepository(uploadDir);
        // ���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
        factory.setSizeThreshold(1024 * 1024);

        // ��ˮƽ��API�ļ��ϴ�����
        ServletFileUpload upload = new ServletFileUpload(factory);

        // �����ϴ�����ļ�
        List<FileItem> list = (List<FileItem>) upload.parseRequest(request);

        for (FileItem item : list)
        {
            // ��ȡ������������
            String name = item.getFieldName();

            // �����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ
            if (item.isFormField())
            {
                // ��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�
                String value = item.getString();

                request.setAttribute(name, value);
            }
            // �Դ���ķ� �򵥵��ַ������д��� ������˵�����Ƶ� ͼƬ����Ӱ��Щ
            else
            {
                *//**
                 * ������������Ҫ��ȡ �ϴ��ļ�������
                 *//*
                // ��ȡ·����
                String value = item.getName();
                // ���������һ����б��
                int start = value.lastIndexOf("\\");
                // ��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�
                String filename = value.substring(start + 1);
                // �ļ���׺��
                String prefix = filename.substring(filename.lastIndexOf(".") + 1);
//                CardCenter cardCenter = new CardCenter();

                request.setAttribute(name, filename);

                // ����д��������
                // ���׳����쳣 ��exception ��׽

                // item.write( new File(path,filename) );//�������ṩ��

                // �ֶ�д��
                // OutputStream out = new FileOutputStream(new File(path,filename));

                InputStream in = item.getInputStream();

                List<CardCenter> listFromExcel = (List<CardCenter>) ExelUtil.exportListFromExcel(in, prefix, cardCenter);

                this.cardCenterService.excleImport(listFromExcel);

                
                 * int length = 0 ; byte [] buf = new byte[1024] ;
                 * 
                 * System.out.println("��ȡ�ϴ��ļ����ܹ���������"+item.getSize());
                 * 
                 * // in.read(buf) ÿ�ζ��������ݴ���� buf ������ while( (length = in.read(buf) ) != -1) { //�� buf ������ ȡ������ д�� ��������������� out.write(buf,
                 * 0, length);
                 * 
                 * }
                 

                in.close();
                // out.close();
            }
        }
    }*/
   
 /*   @RequestMapping("/excleImport")
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        
        System.out.println("----------- upload -----------");  
          
        InputStream is = null;  
        Workbook workbook = null;  
          
        try {  
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);  
            System.out.println("items size is :"+items.size());  
            for (FileItem item : items) {  
                if (item.isFormField()) {  
                    System.out.println(item.getFieldName());  
                    System.out.println(item.getString());  
                } else {  
                    System.out.println(item.getFieldName());  
                    is = item.getInputStream();  
                    workbook = Workbook.getWorkbook(is);  
                }  
            }  
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        } catch (BiffException e) {  
            e.printStackTrace();  
        }  
          
          
        Sheet[] sheetNum = workbook.getSheets();  
           System.out.println("��ӡsheet�Ă�����"+sheetNum.length);  
           Sheet sheet = workbook.getSheet(0);  
           Cell cell = null;  
  
           int columnCount = sheet.getColumns();  
           int rowCount = sheet.getRows();  
           for (int i = 0; i < rowCount; i++) {  
               for (int j = 0; j < columnCount; j++) {  
                  cell = sheet.getCell(j, i);  
                  System.out.print(cell.getContents());  
               }  
               System.out.println(" \n");  
           }  
           resp.sendRedirect("success");     
           workbook.close();  
    }*/
    
    
    /** 
     * �ϴ�Excel  
     * @throws Exception  
     */  
     /*  @POST  
       @Consumes(MediaType.MULTIPART_FORM_DATA)  
     �˴���������ΪTXT_HTML������ExtJSǰ̨���ܻ����ȷ�ķ���   
      @Produces("text/html;charset=utf-8")  
      @Path(value = "uploadExcel")  
      @RequestMapping("/excleImport")
      public String loadFile(@Context HttpServletRequest request) throws Exception {  
        // �����־  
        LogHelper.log(StaffCtrl.class, LogHelper.INFO, ACTION_NAME + "�ϴ�Excel" + START);  
        UploadResponse res = new UploadResponse();  
  
  
            //��ȡ��ǰ·��  
            String tmpDir = System.getProperty("java.io.tmpdir");  
            //���������  
            final int permitedSize = 3 * 1024 * 1024;  
            MultipartRequestWrapper multipartRequest = new MultipartRequestWrapper(request, tmpDir, permitedSize, "UTF-8");  
  
  
            //��ȡ�ļ�����Ҫ���ļ�·����  
            File fileIn = multipartRequest.getFile("filePath");  
            //���ļ���װ��������  
            InputStream is = new FileInputStream(fileIn);  
            //���������б�RowData�����б���Ϣ��  
            List<RowData> dates = ExcelHelper.read(new ArchivesExcelResolver(), is);  
            //��excel�ڵģ�RowData�����б���Ϣ�����в���  
            String errMsg  = staffService.uploadExcelDatas(dates);  
            if("".equals(errMsg)){  
                res.setSuccess(true);  
                res.setResult("success");  
            }else {  
                res.setSuccess(false);  
                res.setResult(errMsg);  
            }  
        LogHelper.log(StaffCtrl.class, LogHelper.INFO, ACTION_NAME + "�ϴ�Excel" + END);  
        ObjectMapper ob = new ObjectMapper();  
        String resStr = ob.writeValueAsString(res);  
        return resStr;  
    }  
  
  
}  */
    
    
    
}  

