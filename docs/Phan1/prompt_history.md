# Lịch sử làm việc với AI

## Vòng 1

AI xác định đoạn mã đang vi phạm nguyên tắc Open/Closed Principle vì toàn bộ thuật toán tính chi phí được viết bằng chuỗi if-else trong cùng một phương thức.

Đánh giá:

Tôi đồng ý với nhận định này vì mỗi lần bổ sung loại địa hình mới đều phải chỉnh sửa trực tiếp Service.

---

## Vòng 2

AI đề xuất sử dụng Strategy Pattern kết hợp Factory Method.

AI cũng giải thích:

* Strategy dùng để tách riêng thuật toán.
* Factory dùng để lựa chọn Strategy phù hợp.

Đánh giá:

Sau khi so sánh với Template Method, tôi lựa chọn Strategy vì thuật toán giữa các loại địa hình hoàn toàn khác nhau.

---

## Vòng 3

AI không sinh code ngay mà đề xuất trách nhiệm từng thành phần.

MaintenanceStrategy

* Định nghĩa thuật toán tính toán.

Các Strategy

* Chỉ xử lý một loại địa hình.

Factory

* Chọn Strategy.

FarmMaintenanceService

* Điều phối.

Đánh giá:

Việc phân chia này giúp mỗi lớp chỉ có một trách nhiệm.

---

## Vòng 4

AI sinh lại FarmMaintenanceService.

Kiểm tra lại:

* Không còn if-else.
* Không chứa thuật toán.
* Chỉ điều phối các thành phần khác.

Kết luận:

Kiến trúc sau khi tái cấu trúc đáp ứng nguyên tắc Open/Closed Principle.
