package log;

public class LogDTO {
	private String startTime;
	private String tranId;
	private String length;
	private String callTime;
	private String beforeMs;
	private String marshaling;
	private String invoking;
	private String unmarshalling;
	private String endTime;
	
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		
		result.append(startTime);
		result.append(", ");
		result.append(endTime);
		result.append(", ");
		result.append(tranId);
		result.append(", ");
		result.append(length);
		result.append(", ");
		result.append(callTime);
		result.append(", ");
		result.append(beforeMs);
		result.append(", ");
		result.append(marshaling);
		result.append(", ");
		result.append(invoking);
		result.append(", ");
		result.append(unmarshalling);
		
		return result.toString();
	}
	
	public boolean checkDto( ) {
		if(startTime == null || tranId == null || 
				length == null || callTime == null || beforeMs == null ||
				marshaling == null || invoking == null || unmarshalling == null ||
				endTime == null) {
			return false;
		}
		return true;
	}
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	public String getBeforeMs() {
		return beforeMs;
	}
	public void setBeforeMs(String beforeMs) {
		this.beforeMs = beforeMs;
	}
	public String getMarshaling() {
		return marshaling;
	}
	public void setMarshaling(String marshaling) {
		this.marshaling = marshaling;
	}
	public String getInvoking() {
		return invoking;
	}
	public void setInvoking(String invoking) {
		this.invoking = invoking;
	}
	public String getUnmarshalling() {
		return unmarshalling;
	}
	public void setUnmarshalling(String unmarshalling) {
		this.unmarshalling = unmarshalling;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
