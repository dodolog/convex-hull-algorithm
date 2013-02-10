package model;

import java.util.ArrayList;

public class Punkty {
	
	static private Integer[] x;
	static private Integer[] y;
	static private Integer[] extremaPoint;
	static private ArrayList<Integer> path;
	static private Boolean[] canDrag;
	static private Double[] angle;
	static private int currentPoint;
	static private String label;
	
	static public int pointReference;
	
	static {
		extremaPoint = new Integer[4];
		path = new ArrayList<Integer>();
	}
	
	public Punkty (){
	}
	

public static int getY(int i) {
	return y[i];
}
public static int getX(int i) {
	return x[i];
}	

public static void setY(int i, int yy) {
	 y[i] = yy;
}
public static void setX(int i, int xx) {
	 x[i] = xx;
}	

public static boolean getCanDrag(int i){
	return canDrag[i];
}
public static void setCanDrag(int i,boolean stan){
	canDrag[i] = stan;
}
public static void setCanDrag(Boolean[] listaStanow){
	canDrag = listaStanow;
}
public static void setCanDragFalse(){
	
	canDrag = new Boolean[size()];
	
	for(int i=0;i<size();i++)
		canDrag[i] = false;
}


public static void setX(Integer[] xx) {
	x = xx;
}

public static void setY(Integer[] yy) {
	y = yy;
}


public static void calculateAngleFor(int i){
	if(Punkty.size() > 0){
		
	int dx,dy;
	double ds;
	for(int n=0;n<Punkty.size();n++) {
		dy = y[n] - y[i];
		dx = x[n] - x[i];
		ds = Math.sqrt( dx*dx + dy*dy );
		
		angle[n] = ( ds == 0.0) ? 0.0 : Math.acos(dx / ds)*180.0/Math.PI;
	}
	}
	
}
public static double getAngle(int i)
{
	return angle[i];
}

public static void losujPunkty(int ile, int max) {
		x = new Integer[ile];
		y = new Integer[ile];
		angle = new Double[ile];
		canDrag = new Boolean[ile];
		
		
		for (int i = 0; i < ile; i++) {
			
			//this.x.set(i,(int) Math.round(Math.random()*max));
			//System.out.println("x "+x.get(i));
			
			//this.y.set(i,  (int) Math.round(Math.random()*max));
			x[i]=(int) Math.round(Math.random()*max);
			y[i]=(int) Math.round(Math.random()*max);
			
			canDrag[i] = false;
		}
	}
	
	public static  int size(){
		
		if(x==null)
			return 0;
		else
			return x.length;
	}
	public static  int sizePath(){
		if(path==null)
			return 0;
		else
			return path.size();
	}

	public static void setExtrema(int i, int n) {
		extremaPoint[i] = n;
	}
	public static int getExtrema(int i) {
		if(extremaPoint[0] == null)
			return -1;
		return extremaPoint[i];
	}

	public static void setCurrentPoint(int currentPoint) {
		Punkty.currentPoint = currentPoint;
	}

	public static int getCurrentPoint() {
		return currentPoint;
	}
	
	public static Integer getPath(int i){
		if(path.size() != 0)
			return path.get(i);
		return Integer.MIN_VALUE;
	}

	public static void setLabel(String label) {
		Punkty.label = label;
	}

	public static String getLabel() {
		return label;
	}

	public static void setAngle(Double[] angle) {
		Punkty.angle = angle;
	}

	public static Double[] getAngle() {
		return angle;
	}

	public static boolean isInPath(int i) {
		
		if(path.contains(i))
			return true;

		return false;
	}
	
	public static void calculateExtremaPoint() {
				
		int temp1 = 0; int a = 0;
		int temp2 = 0; int b = 0;
		int temp3 = Integer.MAX_VALUE; int c = 0;
		int temp4 = Integer.MAX_VALUE; int d = 0;
		
		for(int i=0;i<x.length; i++){
			if( temp1 < x[i]){	temp1 = x[i]; a = i;}
			if( temp2 < y[i]){	temp2 = y[i]; b = i;}
			if( temp3 > x[i]){	temp3 = x[i]; c = i;}
			if( temp4 > y[i]){	temp4 = y[i]; d = i;}
		}
		
		// w przeciwnym kierunku niż ruch wskazówek zegara
		extremaPoint[0] = a;	// prawa krawędź
		extremaPoint[1] = b;    // dolna część
		extremaPoint[2] = c; 	// lewa część
		extremaPoint[3] = d; 	// góra
		
		
		for(int i=0;i<4;i++)
			System.out.print("Ekstream point: "+ extremaPoint[i]+"\n");
	}
	
	public static void computePath(){
		path.clear();				// wyczyść listę z punktami tworzących otoczkę wypukłą 
		calculateExtremaPoint();	// wyznacz 4 punkty eksremalna w lini góra-dół, lewa-prawa
		
		// wyznaczaj pukty otoczki w kolejnych ćwiartkach 
		computationOfLeftBottomPath();
		computationOfBottomRightPath();
		computationOfRightTopPath();
		computationOfTopLeftPath();
	}
	
	private static void computationOfLeftBottomPath(){
		//0 i 1
				
		path.addAll(
		(new Object() {   
			Boolean flag = true;
			ArrayList<Integer> pathTemp = new ArrayList<Integer>();
			
			public ArrayList<Integer> computationPath(int left, int bottom) {
								
				if(flag)
				{
					flag = false;
					pathTemp.clear();
					pathTemp.add( left );  // zawsze dodaj ten  punkt ekstremalny (punkt przy lewej krawędzi) 
				} 
								
				double tempAngle=0.0;
				int temp=-1;
				
				Punkty.calculateAngleFor(left);
				
				for(int i=0; i<x.length; i++) 
				{
					if(x[i] <= x[bottom] && x[i] >= x[left] 
					&& y[i] >= y[left] && y[i] <= y[bottom] ) 
					{ 
					// w następującej metodzie oblicza dla wszystkich kąt !!!		
						if(Punkty.getAngle(i) > tempAngle) 
						{ 
							// tutaj licz kąt względem 'left' punkt odniesienia
							tempAngle = Punkty.getAngle(i);
							temp = i;
						}	
					}	
				}
				System.out.printf("temp:  "+temp+"\n");
				
				
				if(temp != -1){
					pathTemp.add(temp);
					return computationPath(temp, extremaPoint[1]);
				}
				else
					return pathTemp;
						
			}
		} ).computationPath(extremaPoint[2],extremaPoint[1])
		);
		
	}

	private static void computationOfBottomRightPath(){
		//0 i 1
				
		
		/**/
		 path.addAll(  
				 
		(new Object() {   
			
			Boolean flag = true;
			ArrayList<Integer> pathTemp = new ArrayList<Integer>();
			
			
			public ArrayList<Integer>  computationPath(int bottom, int right) {
								
				if(flag)
				{
					flag = false;
					pathTemp.clear();
					pathTemp.add(bottom);
				}
				double tempAngle=90.0;//90
				int temp=-1;

				Punkty.calculateAngleFor(bottom);	
				
				for(int i=0; i<x.length; i++) 
				{
					if(x[i] > x[bottom] && x[i] <= x[right] 
					&& y[i] >= y[right] && y[i] <= y[bottom] ) 
					{ 
					// w następującej metodzie oblicza dla wszystkich kąt !!!
						//
						System.out.print("DlaPunktu: "+ bottom +" BottomRight#  i="+i+" angle:"+Punkty.getAngle(i)+"\n");
						if(Punkty.getAngle(i) <= tempAngle && Punkty.getAngle(i) > 0.0 ) 
						{ 									// > 0.0 
							tempAngle = Punkty.getAngle(i);
							temp = i;
						}	
					}	
				}
				System.out.printf("temp:  "+temp+"\n");
				
				if(temp != -1){
					pathTemp.add(temp);
					return computationPath(temp, extremaPoint[0]);
				} else
					return pathTemp;
						
			}
		} ).computationPath(extremaPoint[1],extremaPoint[0]));
		/**/
	}
	
	
	private static void computationOfRightTopPath(){
		//0 i 1
				
		
		/**/
		 path.addAll(  
				 
		(new Object() {   
			
			Boolean flag = true;
			ArrayList<Integer> pathTemp = new ArrayList<Integer>();
			
			
			public ArrayList<Integer>  computationPath(int right, int top) {
								
				if(flag)
				{
					flag = false;
					pathTemp.clear();
				}
				double tempAngle=180.0;
				int temp=-1;

				Punkty.calculateAngleFor(right);	
				
				for(int i=0; i<x.length; i++) 
				{
					if(x[i] <= x[right] && x[i] >= x[top] 
					&& y[i] >= y[top] && y[i] <= y[right] ) 
					{ 
						System.out.print("DlaPunktu"+ right + " RightTop # i="+i+" angle:"+Punkty.getAngle(i)+"\n");
					// w następującej metodzie oblicza dla wszystkich kąt !!!		
						if(Punkty.getAngle(i) <= tempAngle  &&  Punkty.getAngle(i) > 0.0) 
						{ 
							tempAngle = Punkty.getAngle(i);
							temp = i;
						}	
					}	
				}
				System.out.printf("temp:  "+temp+"\n");
				
				if(temp != -1){
					pathTemp.add(temp);
					return computationPath(temp, extremaPoint[3]);
				} else
					return pathTemp;
						
			}
		} ).computationPath(extremaPoint[0],extremaPoint[3]));
		/**/
	}
	private static void computationOfTopLeftPath(){
		//0 i 1
				
		
		/**/
		 path.addAll(  
				 
		(new Object() {   
			
			Boolean flag = true;
			ArrayList<Integer> pathTemp = new ArrayList<Integer>();
			
			
			public ArrayList<Integer>  computationPath(int top, int left) {
								
				if(flag)
				{
					flag = false;
					pathTemp.clear();
				}
				double tempAngle=90.0;
				int temp=-1;

				Punkty.calculateAngleFor(top);	
				
				for(int i=0; i<x.length; i++) 
				{
					if(x[i] >= x[left] && x[i] <= x[top] 
					&& y[i] >= y[top] && y[i] <= y[left] ) 
					{ 
						
						
					// w następującej metodzie oblicza dla wszystkich kąt !!!	
					//	System.out.print("i="+i+" angle:"+Punkty.getAngle(i)+"\n");
						if(Punkty.getAngle(i) >= tempAngle  ) 
						{ 
							tempAngle = Punkty.getAngle(i);
							temp = i;
						}	
					}	
				}
				System.out.printf("temp:  "+temp+"\n");
				
				if(temp != -1){
					pathTemp.add(temp);
					return computationPath(temp, extremaPoint[2]);
				} else
					return pathTemp;
						
			}
		} ).computationPath(extremaPoint[3],extremaPoint[2]));
		/**/
	}
}
