# BÁO CÁO PHẦN 1

## Mô tả bài toán

Đoạn mã ban đầu sử dụng nhiều câu lệnh if-else để tính toán chi phí bảo trì theo từng loại địa hình.

Điều này làm cho Service phải biết toàn bộ thuật toán của từng loại hình canh tác.

Khi xuất hiện một loại địa hình mới, lập trình viên buộc phải sửa trực tiếp phương thức `calculateMaintenanceCost()`.

---

## Phân tích

Qua quá trình trao đổi với AI, tôi xác định hệ thống đang vi phạm nguyên tắc Open/Closed Principle.

Nguyên nhân là lớp Service không đóng mà luôn phải chỉnh sửa khi mở rộng nghiệp vụ.

---

## Giải pháp

AI đề xuất sử dụng Strategy Pattern kết hợp Factory Method.

Ý tưởng:

* Mỗi thuật toán được tách thành một Strategy.
* Factory chịu trách nhiệm lựa chọn Strategy.
* Service chỉ điều phối.

Nhờ đó khi bổ sung loại địa hình mới chỉ cần tạo Strategy tương ứng mà không sửa Service.

---

## Đánh giá

Ưu điểm

* Không còn chuỗi if-else.
* Mỗi lớp chỉ có một nhiệm vụ.
* Dễ mở rộng.
* Dễ kiểm thử.

Nhược điểm

* Số lượng class tăng lên.
* Cần thêm Factory để quản lý Strategy.

Tuy nhiên đây là đánh đổi hợp lý để hệ thống dễ bảo trì hơn.

---

## Kết luận

Sau khi refactor, FarmMaintenanceService không còn phụ thuộc vào từng loại địa hình.

Giải pháp đáp ứng nguyên tắc Open/Closed Principle và phù hợp với các hệ thống có nghiệp vụ thường xuyên mở rộng.
