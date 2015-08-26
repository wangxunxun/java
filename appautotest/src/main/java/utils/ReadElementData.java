package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadElementData {
	private String excelpath;
	private String sheet;
	private Sheet readsheet;

	public ReadElementData(String excelpath,String sheet) {
		this.excelpath = excelpath;
		this.sheet = sheet;
    	jxl.Workbook readwb = null;   
		InputStream instream = null;
		try {
			instream = new FileInputStream(excelpath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		try {
			readwb = Workbook.getWorkbook(instream);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		Sheet readsheet = readwb.getSheet(sheet); 
		this.readsheet = readsheet;
	}
	public List readTable(){
		List<String> header =new ArrayList<String>();
		int rsColumns = readsheet.getColumns();   
		int rsRows = readsheet.getRows();   
		List elements = new ArrayList();
		List pages = new ArrayList();
		List tabledata = new ArrayList();
		if (rsRows==0){
			throw new IllegalStateException("There is now row found in excel file [" + excelpath + "], can't "
					+ "generate map from column name to column index. ");
		}


		for (int j = 0; j < rsColumns; j++){
			Cell cell = readsheet.getCell(j, 0);   
			header.add(cell.getContents());       	  
		}   

		for(int i = 1;i<rsRows;i++){
			Cell[] cells = readsheet.getRow(i);
			List element = new ArrayList();
			if(cells[0].getContents()!=""&cells[1].getContents()!=""){
				Map<String,String> location = new HashMap<String,String>();
				location.put(header.get(2), cells[2].getContents());
				location.put(header.get(3), cells[3].getContents());
				element.add(cells[1].getContents());
				element.add(location);
				elements.add(element);
				pages.add(cells[0].getContents());
			}
			if(cells[0].getContents()==""&cells[1].getContents()!=""){
				Map<String,String> location = new HashMap<String,String>();
				location.put(header.get(2), cells[2].getContents());
				location.put(header.get(3), cells[3].getContents());
				element.add(cells[1].getContents());
				element.add(location);
				elements.add(element);
			}			
			
		}
		tabledata.add(pages);
		tabledata.add(elements);		
		return tabledata;
		
		
	}
	public List<Integer> getPageDis(){
		int rsRows = readsheet.getRows(); 
		List<Integer> dis = new ArrayList<Integer>();
		for (int i = 1;i < rsRows; i++){
			Cell[] cells = readsheet.getRow(i);
			if (cells[0].getContents()!=""){
				dis.add(i);
			}
		}
		dis.add(rsRows);
		return dis;

	}
	
	public int count(int start,int end){
		int first = 0;
		int second = 0;
		for (int i = 0;i<start;i++){
			if (readsheet.getCell(1, i).getContents()!=""){
//				System.out.println(readsheet.getCell(1, i).getContents());
				first= first+1;
			}
		}
		for (int j = 0;j<end;j++){
			if (readsheet.getCell(1, j).getContents()!=""){
//				System.out.println(readsheet.getCell(1, j).getContents());
				second = second +1;
			}		
		}		
		return second - first;	
	}
	
	public List realPage(){
		List<Integer> dis = getPageDis();
		List pagecount = new ArrayList();
		for(int i = 0;i<dis.size()-1;i++){		
			int count = count(dis.get(i),dis.get(i+1));
			pagecount.add(count);
		}
		return pagecount;		
	}
	
	public List real(){
		List<Integer> data = realPage();
		List r = new ArrayList();
		for (int i = 0; i<data.size();i++){
			int c = 0;
			for (int j=0;j<=i;j++){
				c = c+data.get(j);
			}
			r.add(c);
		}
		return r;

	}
	
	public List getdata(){
		List tabledata = readTable();
		List<Integer> real = real();
		List data = new ArrayList();
		List tabledata1 = (List) tabledata.get(1);
		for (int i = 0;i <real.size()-1;i++){
			data.add(tabledata1.subList(real.get(i), real.get(i+1)));
		}
		System.out.println(data);
		Map d = new HashMap();
		for (int j =0;j<real.size()-1;j++){
			Map a = new HashMap();

			}
		return data;
		}



}
