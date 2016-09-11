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
    	HSSFSheet hssfSheet=wb.getSheetAt(0);  // 获取第一个Sheet页
    	if(hssfSheet!=null){
    		System.out.println("可以用");
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
	    	HSSFSheet hssfSheet=wb.getSheetAt(0);  // 获取第一个Sheet页
	    	if(hssfSheet!=null){
	    		System.out.println("可以用");
	    	}*/
			Workbook workbook = WorkbookFactory.create(file.getInputStream());  
			/*List<String> sfList = exportListFromExcel(workbook, 0);
			for (int i = 0; i < sfList.size(); i++) {
				System.out.println("【"+sfList.get(i).toString()+"】 ");
			}*/
			Sheet hssfSheet = workbook.getSheetAt(0);  //示意访问sheet  
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
//				System.out.println("【"+sfList.get(i).toString()+"】 ");
				// LOGGER.info("【"+sfList.get(i).toString()+"】 ");
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
                     // 处理日期格式、时间格式
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
                     // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
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
                     // 单元格设置成常规
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
     * 由指定的Sheet导出至List
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

        // 解析公式结果
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
                            // 处理日期格式、时间格式
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
                            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
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
                            // 单元格设置成常规
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
//                LOGGER.info("【" + result + "】 ");
            }
            list.add(sb.toString());
        }
        return list;
    }


    /*@RequestMapping("/excleImport")
    public void excleImport(HttpServletRequest request) throws IOException, Exception
    {

        request.setCharacterEncoding("utf-8"); // 设置编码

        // 获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 获取文件需要上传到的路径
        String path = request.getRealPath("/upload");

        File uploadDir = new File(path);
        if (!uploadDir.exists())
        {
            uploadDir.mkdirs();
        }
        factory.setRepository(uploadDir);
        // 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
        factory.setSizeThreshold(1024 * 1024);

        // 高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 可以上传多个文件
        List<FileItem> list = (List<FileItem>) upload.parseRequest(request);

        for (FileItem item : list)
        {
            // 获取表单的属性名字
            String name = item.getFieldName();

            // 如果获取的 表单信息是普通的 文本 信息
            if (item.isFormField())
            {
                // 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
                String value = item.getString();

                request.setAttribute(name, value);
            }
            // 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
            else
            {
                *//**
                 * 以下三步，主要获取 上传文件的名字
                 *//*
                // 获取路径名
                String value = item.getName();
                // 索引到最后一个反斜杠
                int start = value.lastIndexOf("\\");
                // 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
                String filename = value.substring(start + 1);
                // 文件后缀名
                String prefix = filename.substring(filename.lastIndexOf(".") + 1);
//                CardCenter cardCenter = new CardCenter();

                request.setAttribute(name, filename);

                // 真正写到磁盘上
                // 它抛出的异常 用exception 捕捉

                // item.write( new File(path,filename) );//第三方提供的

                // 手动写的
                // OutputStream out = new FileOutputStream(new File(path,filename));

                InputStream in = item.getInputStream();

                List<CardCenter> listFromExcel = (List<CardCenter>) ExelUtil.exportListFromExcel(in, prefix, cardCenter);

                this.cardCenterService.excleImport(listFromExcel);

                
                 * int length = 0 ; byte [] buf = new byte[1024] ;
                 * 
                 * System.out.println("获取上传文件的总共的容量："+item.getSize());
                 * 
                 * // in.read(buf) 每次读到的数据存放在 buf 数组中 while( (length = in.read(buf) ) != -1) { //在 buf 数组中 取出数据 写到 （输出流）磁盘上 out.write(buf,
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
           System.out.println("打印sheet的個數："+sheetNum.length);  
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
     * 上传Excel  
     * @throws Exception  
     */  
     /*  @POST  
       @Consumes(MediaType.MULTIPART_FORM_DATA)  
     此处必须设置为TXT_HTML，否则ExtJS前台不能获得正确的返回   
      @Produces("text/html;charset=utf-8")  
      @Path(value = "uploadExcel")  
      @RequestMapping("/excleImport")
      public String loadFile(@Context HttpServletRequest request) throws Exception {  
        // 输出日志  
        LogHelper.log(StaffCtrl.class, LogHelper.INFO, ACTION_NAME + "上传Excel" + START);  
        UploadResponse res = new UploadResponse();  
  
  
            //获取当前路径  
            String tmpDir = System.getProperty("java.io.tmpdir");  
            //最大数据量  
            final int permitedSize = 3 * 1024 * 1024;  
            MultipartRequestWrapper multipartRequest = new MultipartRequestWrapper(request, tmpDir, permitedSize, "UTF-8");  
  
  
            //获取文件（主要是文件路径）  
            File fileIn = multipartRequest.getFile("filePath");  
            //将文件包装成输入流  
            InputStream is = new FileInputStream(fileIn);  
            //返回数据列表（RowData类型列表信息）  
            List<RowData> dates = ExcelHelper.read(new ArchivesExcelResolver(), is);  
            //对excel内的（RowData类型列表信息）进行操作  
            String errMsg  = staffService.uploadExcelDatas(dates);  
            if("".equals(errMsg)){  
                res.setSuccess(true);  
                res.setResult("success");  
            }else {  
                res.setSuccess(false);  
                res.setResult(errMsg);  
            }  
        LogHelper.log(StaffCtrl.class, LogHelper.INFO, ACTION_NAME + "上传Excel" + END);  
        ObjectMapper ob = new ObjectMapper();  
        String resStr = ob.writeValueAsString(res);  
        return resStr;  
    }  
  
  
}  */
    
    
    
}  

