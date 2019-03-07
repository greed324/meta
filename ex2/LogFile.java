package log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFile {
	private String threadName = "";	// 
	private int count = 0;
	private StringBuilder fileOut = new StringBuilder(); // 
	StringBuilder result = new StringBuilder();
	
	/**
	 * 로그 파일을 한 문자열씩 읽어서 분석해서  파일로 내보내기
	 */
	public void logAnalysis() {
		try {
			File inFile = new File("C:\\Dev\\workspace\\2.로그파일분석\\galileo.log");
			File outFile = new File("c:\\Dev\\workspace\\2.로그파일분석\\galileo2.log");
			
			FileReader filereader = new FileReader(inFile);
			
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			
			Map<String, LogDTO> map = new HashMap<String, LogDTO>();
			
			while((line = bufReader.readLine()) != null) {
				logData(line,map); // 1번째 실행 하는 메소드
				//System.out.println("line : " +  line);
			}
			
			FileOutputStream fos = new FileOutputStream(outFile);
			fos.write(fileOut.toString().getBytes());
			
			/*System.out.println(++count + fileOut.toString());*/
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 시작시간 데이터 추출 해서 DTO에 저장후, Map에 저장
	 * 
	 * @param map key 값에 쓰레드명이랑  value 값에 DTO을 저장하기 위한 map
	 * @param line 파일에서 읽어 온 문자열
	 */
	private void setStartTime(Map<String, LogDTO> map, String line) {
		// ##galileo_bean start.의 시작시간을 정규표현식으로 추출
		Pattern startTimePattern = Pattern.compile("\\d{2}.\\d{2}.\\d{2} \\d{2}.\\d{2}.\\d{2}");
		Matcher startTimeMacher = startTimePattern.matcher(line);			
		LogDTO logDto = new LogDTO();
		
		// startTimeMacher을 찾았을 때 자른 문자열을 startTime변수에 저장 후  Dto,Map에 저장
		if (startTimeMacher.find()) {
			String startTime = startTimeMacher.group();
			logDto.setStartTime(startTime);
			map.put(threadName, logDto);
			}
	}
	
	/**ESB_TRAN_ID ID 추출 해서 DTO, Map에 저장
	 * @param map key 값에 쓰레드명이랑  value 값에 DTO을 저장하기 위한 map
	 * @param line 파일에서 읽어 온 문자열
	 */
	private void setTranId(Map<String, LogDTO> map, String line) {
		// ESB_TRAN_ID의 ID을 정규표현식으로 추출
		Pattern tranIdPattern = Pattern.compile("(ESB_TRAN_ID : )(.*)");
		Matcher tranIdMacher = tranIdPattern.matcher(line);
		
		// 해당하는 문자열을 찾았을 때 tranId에 문자열을 저장
		if (tranIdMacher.find()) {
			String tranId = tranIdMacher.group(2);
			
			// Map에 있는 쓰레드가 실행이 유무 확인 후 Dto에 저장
			if(map.containsKey(threadName)) {
				LogDTO logDto = map.get(threadName);
				logDto.setTranId(tranId);
			}
		}
	}
	
	/** Content-Length 길이 추출 해서 DTO, Map에 저장
	 * @param map key 값에 쓰레드명이랑  value 값에 DTO을 저장하기 위한 map
	 * @param line 파일에서 읽어 온 문자열
	 */
	private void setLength(Map<String, LogDTO> map, String line) {
		// Content-Length 길이 추출
		Pattern lengthPattern = Pattern.compile("(Content-Length:)(.*)");
		Matcher lengthMacher = lengthPattern.matcher(line);
		
		// 해당하는 문자열을 찾았을 때 length에 문자열을 저장
		if(lengthMacher.find()) {
			String length = lengthMacher.group(2);
			
			// Map에 있는 쓰레드가 실행 유무 확인 후 Dto에 저장
			if(map.containsKey(threadName)) {
				LogDTO logDto = map.get(threadName);
				logDto.setLength(length);
			}
		}
	}
	
	
	/** galileo call time 시간 추출 해서 DTO, Map에 저장
	 * @param map key 값에 쓰레드명이랑  value 값에 DTO을 저장하기 위한 map
	 * @param line 파일에서 읽어 온 문자열
	 */
	private void setCallTime(Map<String, LogDTO> map, String line) {
		// #galileo call time 시간 추출
		Pattern callTimePattern = Pattern.compile("(#galileo call time:)(\\d+)");
		Matcher callTimeMacher = callTimePattern.matcher(line);
		
		if(callTimeMacher.find()) {
			String callTime = callTimeMacher.group(2);
			
			if(map.containsKey(threadName)) {
				LogDTO logDto = map.get(threadName);
				logDto.setCallTime(callTime);
			}
		}
	}
	
	/**
	 * Before Marshalling 시간 추출 해서 DTO, Map에 저장
	 * @param map key 값에 쓰레드명이랑  value 값에 DTO을 저장하기 위한 map
	 * @param line 파일에서 읽어 온 문자열
	 */
	private void setBfMs(Map<String, LogDTO> map, String line) {
		// Before Marshalling 시간 추출
		Pattern beforeMsPattern = Pattern.compile("\\d+");
		Matcher beforeMsMacher = beforeMsPattern.matcher(line);
		
		if(beforeMsMacher.find()) {
			String beforeMs = beforeMsMacher.group();
			
			if(map.containsKey(threadName)) {
				LogDTO logDto = map.get(threadName);
				logDto.setBeforeMs(beforeMs);
			}
		}
	}
	
	/**
	 * Marshalling 시간 추출 해서 DTO, Map에 저장
	 * @param map key 값에 쓰레드명이랑  value 값에 DTO을 저장하기 위한 map
	 * @param line 파일에서 읽어 온 문자열
	 */
	private void setMarshalling(Map<String, LogDTO> map, String line) {
		//Marshalling 시간 추출
		Pattern MsPattern = Pattern.compile("\\d+");
		Matcher MsMacher = MsPattern.matcher(line);
		
		if(MsMacher.find()) {
			String marshaling = MsMacher.group();
			
			if(map.containsKey(threadName)) {
				LogDTO logDto = map.get(threadName);
				logDto.setMarshaling(marshaling);
			}
		}
	}
	
	/**
	 * Unmarshalling and Send to CmmMod Server 시간 추출 해서 DTO, Map에 저장
	 * @param map key 값에 쓰레드명이랑  value 값에 DTO을 저장하기 위한 map
	 * @param line 파일에서 읽어 온 문자열
	 */
	private void setUnmarshalling(Map<String, LogDTO> map, String line) {
		//Unmarshalling and Send to CmmMod Server 시간 추출
		Pattern usPattern = Pattern.compile("\\d+");
		Matcher usMacher = usPattern.matcher(line);
		
		if(usMacher.find()) {
			String unmarshalling = usMacher.group();
			
			if(map.containsKey(threadName)) {
				LogDTO logDto = map.get(threadName);
				logDto.setUnmarshalling(unmarshalling);
			}
		}
	}
	
	/**
	 * invoking 시간 추출 해서 DTO, Map에 저장
	 * @param map key 값에 쓰레드명이랑  value 값에 DTO을 저장하기 위한 map
	 * @param line 파일에서 읽어 온 문자열
	 */
	private void setInvoking(Map<String, LogDTO> map, String line) {
		// invoking 시간 추출
		Pattern invokingPattern = Pattern.compile("\\d+");
		Matcher invokingMacher = invokingPattern.matcher(line);
		
		if(invokingMacher.find()) {
			String invoking = invokingMacher.group();
			
			if(map.containsKey(threadName)) {
				LogDTO logDto = map.get(threadName);
				logDto.setInvoking(invoking);
			}
		}
	}
	
	
	/**
	 * 종료시간 추출 해서 DTO, Map에 저장
	 * @param map key 값에 쓰레드명이랑  value 값에 DTO을 저장하기 위한 map
	 * @param line 파일에서 읽어 온 문자열
	 */
	private void setEndTime(Map<String, LogDTO> map, String line) {
		//##galileo_bean end 종료시간 추출
		Pattern endTimePattern = Pattern.compile("\\d{2}.\\d{2}.\\d{2} \\d{2}.\\d{2}.\\d{2}");
		Matcher endTimeMacher = endTimePattern.matcher(line);

		if(endTimeMacher.find()) {
			String endTime = endTimeMacher.group();
			
			if(map.containsKey(threadName)) {
				LogDTO logDto = map.get(threadName);
				logDto.setEndTime(endTime);
				
				//logDto.toString(); 
				
				
				// null이 아닐 경우
				if(logDto.checkDto() ) {
					fileOut.append(logDto.toString());
					fileOut.append("\n");
					//System.out.println((++count) + " result : " + logDto.toString());
				}
				//map에 있는 쓰레드명 데이터 삭제
				map.remove(threadName);
			}
		}
	}
	
	
	/**
	 * 쓰레드에 데이터를 저장.
	 * @param line
	 * @param start
	 * 
	 */
	private void logData(String line, Map<String,LogDTO> map) {
		// 문자열에 있는 쓰레드명 추출
		Pattern threadPattern = Pattern.compile("[A-Za-z]+.[A-Za-z]+-[A-Za-z]+-[A-Za-z]+-[0-9]+");
		Matcher threadMacher = threadPattern.matcher(line);
		//String threadName = "";
		
		//  쓰레드명 추출
		if (threadMacher.find()) {
			threadName = threadMacher.group();
			// System.out.println(threadMacher.group());
		}
		// 쓰레드 이름이 안나올 경우 
		if ("".compareTo(threadName) == 0) return;
		
		// 문자열에 galileo_bean start 키워드를 포함할 경우
		if (line.contains("##galileo_bean start.")) {
			setStartTime(map, line);
		}
		// 문자열에 ESB_TRAN_ID가 키워드를 포함할 경우
		else if(line.contains("ESB_TRAN_ID")) {
			setTranId(map, line);
		}
		// 문자열에 Content_Length가 키워드를 포함할 경우
		else if(line.contains("Content-Length")) {
			setLength(map, line);
		}		
		// 문자열에 galileo call time가 키워드를 포함할 경우
		else if(line.contains("#galileo call time")) {
			setCallTime(map, line);
		}
		// 문자열에 Before Marshalling가 키워드를 포함할 경우
		else if(line.contains("Before Marshalling")) {
			setBfMs(map, line);
		}
		// 문자열에 Marshalling가 키워드를 포함할 경우
		else if(line.contains("Marshalling")) {
			setMarshalling(map, line);
		}
		// 문자열에 Invoking galileo가 키워드를 포함할 경우
		else if(line.contains("Invoking galileo")) {
			setInvoking(map, line);
		}
		// 문자열에 Unmarshalling and Send to CmmMod Server가 키워드를 포함할 경우
		else if(line.contains("Unmarshalling and Send to CmmMod Server")) {
			setUnmarshalling(map, line);
		}
		// 문자열에 galileo_bean end가 키워드를 포함할 경우
		else if(line.contains("##galileo_bean end")) {
			setEndTime(map, line);
		}
	}
	
	
	/**
	 *  로그 파일을 분 단위로 분석을 해서 출력
	 */
	public void logAnalysisWrite() {
		FileReader filereader = null;
		BufferedReader bufReader = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			File inFile = new File("C:\\Dev\\workspace\\2.로그파일분석\\galileo2.log");
			File outFile = new File("c:\\Dev\\workspace\\2.로그파일분석\\galileo3.log");
			
			filereader = new FileReader(inFile);
			bufReader = new BufferedReader(filereader);
			
			String line = "";
			
			Map<String, List<LogDTO2>> map = new LinkedHashMap<String, List<LogDTO2>>();
			
			while((line = bufReader.readLine()) != null) {
				collectionMinutely(line, map);
				//System.out.println(++count + "line : " +  line);
			}
			analyzeMinutely(map);
			
			fileWriter = new FileWriter(outFile);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(result.toString());
			
			//System.out.println(++count + fileOut.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(bufReader != null) bufReader.close();
				if(filereader != null) filereader.close();
				if(bufferedWriter != null) bufferedWriter.close();
				if(fileWriter != null) fileWriter.close();
			}catch (Exception e) {
				
			}
		}
	}
	
	
	/** 시작시간, 종료시간, 길이 추출
	 * map에 로그 데이터를 분단위로 모아준다.
	 * @param line
	 * @param map
	 */
	private void collectionMinutely(String line, Map<String, List<LogDTO2>> map) {
		// 콤마를 기준으로 문자열을 자른다
		String[] datas = line.split(",");	
		LogDTO2 logDto = new LogDTO2();
		logDto.setStartTime(datas[0]);
		logDto.setEndTime(datas[1]);
		logDto.setLength(datas[3]);
		
		String minute = logDto.getStartTime().substring(0, 14);
		//System.out.println("minute : " + minute);
		
		// map 안에 분이 있을 경우
		if(map.containsKey(minute)) {
			//map 에서 리스트를 가지고 와서 dto를 추가 해준다 
			map.get(minute).add(logDto);
			
		} 
		// map 안에 아무것도 없을 경우
		else {
			List<LogDTO2> list = new ArrayList<LogDTO2>();
			list.add(logDto);
			map.put(minute, list);
		}
	}
	
	/**
	 * logData를 분 단위로 묶어서 데이터를 추출
	 * 
	 * @param map
	 */
	private void analyzeMinutely(Map<String, List<LogDTO2>> map) {
		// map에 있는 모든 key를 가지고 온다.
		Set<String> keys = map.keySet();

		SimpleDateFormat sdf = new SimpleDateFormat("YY.MM.DD hh:mm:ss");

		/*
		 * 평균 소요시간 : 종료시간 - 시작시간 최소 시간 : 제일 작은시간 최대 : 제일 큰 시간
		 */
		try {
			for (String key : keys) {
				// map에 있는 key에 맞는 value을 가지고 온다.
				List<LogDTO2> list = map.get(key);
				LogDTO2 firstDto = list.get(0);
				int size = list.size();
				int totalLength = 0;
				int avgLength = 0;
				int maxLength = Integer.parseInt(firstDto.getLength().trim());
				int minLength = Integer.parseInt(firstDto.getLength().trim());
				long avgTime = 0;
				long totalTime = 0;
				long maxTime = sdf.parse(firstDto.getEndTime()).getTime() - sdf.parse(firstDto.getStartTime()).getTime();
				long minTime = sdf.parse(firstDto.getEndTime()).getTime() - sdf.parse(firstDto.getStartTime()).getTime();
				
				for (int i = 0; i < size; i++) {
					// list에 있는 dto를 가지고 온다.
					LogDTO2 logDto2 = list.get(i);
					String length = logDto2.getLength().trim();
					// 
					int numLength = Integer.parseInt(length);
					// contentlength 의 총합
					totalLength += numLength;
					
					// numLength가 maxLength보다 클 경우 numLength값을 maxLength 저장
					if (maxLength < numLength) {
						maxLength = numLength;
					}
					// numLength가 minLength보다 작을 경우 numLength값을 minLength 저장
					else if (minLength > numLength) {
						minLength = numLength;
					}
					
					String startTime = logDto2.getStartTime().trim();
					String endTime = logDto2.getEndTime().trim();
					
					long time = sdf.parse(endTime).getTime() - sdf.parse(startTime).getTime();
					totalTime += time;
					
					if (maxTime < time ) {
						maxTime = time;
					}
					else if (minTime > time) {
						minTime = time;
					}
					
				}
				// contentlength 의 평균 길이
				avgLength = totalLength / size;
				avgTime = totalTime / size;
				
				result.append(key);
				result.append(", ");
				result.append(size);
				result.append(", ");
				result.append(avgTime);
				result.append(", ");
				result.append(minTime);
				result.append(", ");
				result.append(maxTime);
				result.append(", ");
				result.append(avgLength);
				result.append(", ");
				result.append(minLength);
				result.append(", ");
				result.append(maxLength);
				result.append("\n");
				//System.out.println(" result : " + result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
