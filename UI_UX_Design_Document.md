# Tài liệu Thiết kế UI/UX - Ứng dụng "Kid Taps"

Dựa trên các mockup (`Kid Taps Logo.html`, `Kid Taps Splash.html`, `Kid Taps Tablet.html`), dưới đây là bản thiết kế hệ thống UI/UX hoàn chỉnh để tiến hành code cho ứng dụng (Android/iOS/Web).

Phong cách thiết kế chủ đạo là **Neo-brutalism / Arcade vui nhộn**, rất phù hợp với trẻ em: sử dụng màu sắc nguyên bản (primary colors), viền đen dày (thick strokes), bóng đổ cứng (hard shadows) và các khối bo tròn lớn.

---

## 1. Hệ thống Màu sắc (Color Palette)

Màu sắc cần tươi sáng, độ tương phản cao, kích thích thị giác của trẻ:

*   🔴 **Red (Đỏ):** `#FF3B3B` - Dùng cho cảnh báo, hình khối (Shapes), nút Thoát/Reset, nút chơi lại.
*   🟡 **Yellow (Vàng):** `#FFD23F` - Màu thương hiệu chính, dùng cho nút bấm chính (Primary Button), các icon nổi bật, sao điểm thưởng.
*   🔵 **Blue (Xanh lam):** `#1E88FF` - Dùng cho các thanh tiến trình (progress bar), màn hình đếm ngược, text nhấn mạnh.
*   🟢 **Green (Xanh lá):** `#22C55E` - Dùng cho trạng thái bật (Toggle ON), điểm số cộng thêm (Score burst), nút thành công.
*   ⚪ **Cream (Kem/Nền chính):** `#FFF8EC` - Màu nền chủ đạo của toàn bộ app, giúp làm dịu mắt so với màu trắng toát.
*   🟤 **Soft (Nền phụ):** `#F4ECDA` - Dùng cho các rãnh trượt (slider track), nền mờ phía sau.
*   ⚫ **Ink (Mực đen/Viền):** `#1A1A2E` - Màu đen hơi ám xanh, dùng cho text chính, viền khối (border), và bóng đổ cứng (hard shadow).

---

## 2. Typography (Kiểu chữ)

Sự kết hợp giữa phông chữ bo tròn vui nhộn và phông chữ kỹ thuật số gọn gàng:

1.  **Phông chữ chính (Headings, Nút bấm lớn, Điểm số):** `Fredoka`
    *   Đặc điểm: Chữ bo tròn, thân thiện, dễ đọc cho trẻ.
    *   Trọng lượng (Weight): 500, 600, 700 (Thường dùng 700 - Bold cho Heading).
2.  **Phông chữ phụ (UI Text, Label, Settings, Sidebar):** `Space Grotesk`
    *   Đặc điểm: Cứng cáp hơn một chút, mang hơi hướng tương lai/arcade. Dễ đọc ở kích thước nhỏ.
    *   Trọng lượng: 500, 600, 700.

---

## 3. UI Components Cốt lõi (Core Components)

Để duy trì tính đồng nhất, cần xây dựng các UI Widget sau:

*   **Khối/Thẻ (Cards):**
    *   Viền: `3px solid var(--ink)`
    *   Bo góc (Border-radius): `28px`
    *   Bóng đổ cứng: `box-shadow: 0 6px 0 var(--ink)`
    *   Nền: `Cream`, `Yellow` hoặc theo chủ đề của card.
*   **Nút bấm (Buttons):**
    *   **Nút chính (Primary):** Bo góc `20px`, viền đen `3px`, bóng đen dưới đáy `0 5px 0 var(--ink)`. Màu nền thường là Vàng (Yellow). Khi ấn xuống (Pressed State), nút sẽ tụt xuống (dịch Y khoảng 5px) và mất bóng đổ.
    *   **Nút phụ (Pill Buttons):** Bo góc tròn tuyệt đối `999px`, viền `2px solid var(--ink)`, dùng cho các tag nhỏ (VD: Chế độ nhanh, Hiển thị ngôn ngữ).
*   **Công tắc (Toggle Switch):**
    *   Hạt công tắc (Thumb) thụt vào trong viền đen.
    *   Màu nền tắt là `Soft`, màu nền bật là `Green`.
*   **Thanh trượt (Slider):**
    *   Rãnh trượt `Soft`, phần đã trượt màu `Blue`.
    *   Nút trượt (Knob) là một vòng tròn màu `Yellow` có viền đen và bóng đen.

---

## 4. Flow UX và Các màn hình chính (Tablet Landscape)

App được tối ưu cho giao diện ngang (Landscape) của Tablet với tỷ lệ 4:3 hoặc 16:10.

### 4.1. Màn hình Chờ / Splash (Splash Animations)
*   **UX:** Hiển thị 1 lần khi mở app (khoảng 3 giây). Có các hiệu ứng vui nhộn (Khối nảy, Lottie Mascot, Đồng xu rơi).
*   **Mục đích:** Gây ấn tượng mạnh lập tức, tạo cảm giác như máy chơi game arcade.

### 4.2. Màn hình Chính (Home - `THome`)
*   **Bố cục:** Sidebar bên trái (Home, Play, Scores, Settings) + Nội dung bên phải.
*   **Tính năng:**
    *   Thanh sidebar chứa logo, nút đổi ngôn ngữ (EN/VI) và chế độ giáo viên (Teacher Mode).
    *   Vùng nội dung hiển thị các thẻ trò chơi lớn (Ví dụ: Shapes, Animals) với màu sắc nổi bật (Đỏ, Xanh) kèm theo mô tả và điểm cao nhất.

### 4.3. Cài đặt Giáo viên / Phụ huynh (Teacher Setup - `TSetup`)
*   **UX:** Trước khi vào chơi, phụ huynh/giáo viên có thể tinh chỉnh để phù hợp với độ tuổi của bé.
*   **Tính năng:**
    *   Tăng giảm tuổi (Auto-suggest difficulty).
    *   Slider chỉnh tốc độ xuất hiện (Spawn speed), thời gian hiển thị (Hold time), và điểm mục tiêu (Target score).
    *   Toggle bật/tắt nhạc, chế độ "Calm" (giảm hiệu ứng), giọng nói đọc tên, và rung (haptics).

### 4.4. Đếm ngược (Countdown - `TCountdown`)
*   Hiệu ứng số đếm lùi 3 - 2 - 1 với vòng tròn tỏa ra (ripple effect) trên nền Xanh lam. Giúp trẻ chuẩn bị tinh thần tập trung.

### 4.5. Màn hình Chơi (Game Active - `TGame`)
*   **UI:** Nền sáng ấm (Gradient Cream). Có lưới mờ (grid) phía sau tăng tính arcade. Thanh HUD ở trên cùng (Thời gian, Tiến trình điểm, Nút Pause).
*   **UX:** Các hình khối/con vật xuất hiện ngẫu nhiên. Khi bé chạm đúng:
    *   Khối thu nhỏ/vỡ ra (Ripple effect).
    *   Text điểm (+1) nảy lên (Score burst).
    *   Âm thanh haptics nhẹ + Giọng đọc tên hình/con vật.
*   **Pause Overlay:** Làm mờ màn hình, hiển thị popup thân thiện ("Take a breath") với nút Resume và Home.

### 4.6. Kết quả (Result - `TResult`)
*   **UX:** Màn hình tưởng thưởng (Rewarding). Pháo giấy (Confetti) rơi xuống.
*   **UI:** Nền màu vàng rực. Điểm số to ở trung tâm. Hiển thị số sao (1-3 sao) và các thống kê (Thời gian, Độ chính xác, Chuỗi liên tiếp).
*   **Hành động:** Chơi lại (Red button) hoặc Về nhà (Cream button).

### 4.7. Chuyển ngữ (Language Loading - `TLangLoad`)
*   UX chuyển đổi mượt mà khi đổi giữa Tiếng Anh và Tiếng Việt. Hiển thị hiệu ứng hành tinh quay quanh quỹ đạo và thẻ cờ lật (EN -> VI), nhằm giữ trẻ khỏi nhàm chán trong thời gian tải dữ liệu/âm thanh.

### 4.8. Cổng Phụ Huynh (Parent Settings - `TSettings`)
*   **UX Cổng an toàn (Parent Gate):** Yêu cầu nhấn giữ nút Ổ Khóa (Lock) trong 3 giây (Vòng tròn màu lấp đầy từ từ) để tránh trẻ em tự ý vào đổi cài đặt.
*   Trong này chứa tùy chọn Xóa dữ liệu (Reset scores), Âm lượng, và Chuyển ngôn ngữ hệ thống.

---

## 5. Hướng triển khai (Implementation Plan)

Nếu code bằng **React Native**, **Flutter** hoặc **Android Jetpack Compose**, bạn cần tuân theo quy tắc sau để giữ đúng thiết kế:

1.  **Định nghĩa Global Theme:** Áp dụng mã hex color và font chữ (`Fredoka`, `Space Grotesk`) vào toàn cục.
2.  **Tạo Custom Modifier / Widget cho Shadow:** Bóng đổ trong thiết kế này không phải là shadow tỏa mờ (blur) mà là shadow cứng (offset Y, blur 0). Ví dụ trên Android/Compose:
    ```kotlin
    Modifier.background(Cream, RoundedCornerShape(28.dp))
            .border(3.dp, Ink, RoundedCornerShape(28.dp))
            .offset(y = (-6).dp) // Giả lập shadow cứng
    ```
3.  **Animations:** Dùng `Lottie` cho màn splash và confetti. Dùng `Spring Animation` cho các nút bấm (khi nhấn vào nút thu nhỏ lại và mất bóng) và cho các pop-up (Scale từ 0.8 lên 1.0 kèm nảy).
4.  **Âm thanh & Haptic:** UX của game trẻ em cực kỳ phụ thuộc vào âm thanh. Cần có file audio đọc tên (Voice prompts), tiếng "pop" khi chạm đúng, và rung nhẹ thiết bị (Vibration/Haptic Feedback).
