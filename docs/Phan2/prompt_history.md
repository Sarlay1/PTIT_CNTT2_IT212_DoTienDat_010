# Lịch sử làm việc với AI

## Vòng 1

AI phân tích nguyên nhân của lỗi `TransientPropertyValueException`.

Kết quả:

* `SensorDevice` được tạo bằng từ khóa `new` nên đang ở trạng thái **Transient**.
* `DailyReport` tham chiếu đến `SensorDevice` nhưng Hibernate chưa quản lý đối tượng này.
* Khi gọi `reportRepo.save(report)`, Hibernate chỉ lưu `DailyReport`, dẫn đến lỗi vì `SensorDevice` chưa được persist.

Đánh giá:

AI xác định đúng nguyên nhân gốc rễ của lỗi, không phải do Transaction hay Repository.

---

## Vòng 2

AI đề xuất ba phương án:

* Sử dụng `CascadeType.PERSIST`.
* Sử dụng `CascadeType.ALL`.
* Lưu `SensorDevice` trước bằng `SensorRepository`.

Đánh giá:

Sau khi so sánh, tôi chọn `CascadeType.PERSIST` vì chỉ cần lan truyền thao tác lưu dữ liệu, không ảnh hưởng đến các thao tác khác như `REMOVE` hoặc `DETACH`.

---

## Vòng 3

AI được yêu cầu chỉ sửa trong phạm vi đoạn mã được cung cấp.

Kết quả:

* Giữ nguyên logic của `SensorDataService`.
* Đề xuất bổ sung `cascade = CascadeType.PERSIST` trong quan hệ `@ManyToOne` của Entity `DailyReport`.

Đánh giá:

Giải pháp phù hợp với yêu cầu đề bài và không làm thay đổi kiến trúc hiện có.

---

## Vòng 4

AI đề xuất sử dụng `@RestControllerAdvice` kết hợp `@ExceptionHandler(DataAccessException.class)` để xử lý lỗi cơ sở dữ liệu.

Đánh giá:

Giải pháp giúp API trả về JSON thống nhất thay vì HTTP 500 mặc định, cải thiện khả năng xử lý lỗi của hệ thống.
