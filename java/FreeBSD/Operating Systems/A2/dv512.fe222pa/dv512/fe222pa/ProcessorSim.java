package dv512.fe222pa;
public class ProcessorSim {
  int processingTime;
  int id;
  int burstTime;
  int arrivalTime;
  int totalWaitingTime;

  public int getTotalWaitingTime() {
    return this.totalWaitingTime;
  }

  public void setTotalWaitingTime(int totalWaitingTime) {
    this.totalWaitingTime = totalWaitingTime;
  }

  

  public ProcessorSim(int id, int burstTime, int arrivalTime) {
    this.id = id;
    this.burstTime = burstTime;
    this.arrivalTime = arrivalTime;
    this.processingTime = 0;
  }

  public int getProcessingTime() {
    return this.processingTime;
  }

  public void setProcessingTime(int processingTime) {
    this.processingTime = processingTime;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBurstTime() {
    return this.burstTime;
  }

  public void setBurstTime(int burstTime) {
    this.burstTime = burstTime;
  }

  public int getArrivalTime() {
    return this.arrivalTime;
  }

  public void setArrivalTime(int arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  

  
}
