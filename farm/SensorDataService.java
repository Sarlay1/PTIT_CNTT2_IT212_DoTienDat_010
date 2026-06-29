@Service
public class SensorDataService {

    private final DailyReportRepository reportRepo;

    public SensorDataService(DailyReportRepository reportRepo) {
        this.reportRepo = reportRepo;
    }

    @Transactional
    public ReportResponse createDailyReport(ReportRequest request) {

        System.out.println("Đang xử lý dữ liệu từ thiết bị: "
                + request.getDeviceCode());

        // SensorDevice đang ở trạng thái Transient
        SensorDevice newSensor = new SensorDevice();
        newSensor.setDeviceCode(request.getDeviceCode());
        newSensor.setStatus("ACTIVE");

        // Tạo báo cáo và liên kết với SensorDevice
        DailyReport report = new DailyReport();
        report.setTemperature(request.getTemp());
        report.setHumidity(request.getHumidity());
        report.setSensorDevice(newSensor);

        /*
         * Khi Entity DailyReport được cấu hình:
         * @ManyToOne(cascade = CascadeType.PERSIST)
         * Hibernate sẽ tự động persist SensorDevice trước khi lưu DailyReport.
         */
        reportRepo.save(report);

        return new ReportResponse(report.getId(), "SUCCESS");
    }
}