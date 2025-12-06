ٍ# 🛒 Online Shopping System (Java Console Application)

![Java](https://img.shields.io/badge/Java-Console%20App-orange)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen)
![License](https://img.shields.io/badge/License-Open--Source-blue)

---

## 📌 Description

This is a **Java console-based Online Shopping System** that simulates a real e-commerce shopping experience.

Users can browse categories, view products, add items to the cart, apply discount coupons, enter personal information, and finally **generate an invoice** saved to a text file.

The system is fully menu-driven and supports continuous interaction until the user exits.

---

## 🧠 Features

✔ Display product categories  
✔ Display products with prices  
✔ Add items to shopping cart with quantity  
✔ View all items in cart + total price  
✔ Apply discount coupons: 

=======DISCOUNT=========

✔ SALE10 → 10%

✔ VIP20 → 20%

✔ MEGA30 → 30%






✔ Collect user info during checkout  
✔ Generate unique order number  
✔ Save invoice to `invoice.txt`  
✔ Clean menu and user-friendly I/O  
✔ Input validation



## 🗂 Project Structure


src/
├─ app/
│ └─ Main.java
│


├─ model/
│ ├─ Product.java
│ ├─ CartItem.java
│ └─ User.java
│


├─ service/
│ ├─ CartService.java
│ └─ ShoppingService.java
│

└─ util/
├─ Utils.java
└─ Validation.java



---

## 🖥️ Menu Overview



===== ONLINE SHOPPING SYSTEM =====

Display Categories

Display Products

Add to Cart

View Cart

Apply Discount

Checkout

Exit
Enter choice:







---

## 📄 Sample Invoice Output


===== INVOICE =====
Order Number: ORD-69055

Customer: Mohamed

Phone: 01155626246

Address: Assiut

Bag x 3 = 450.0 EGP

Total: 450.0 EGP

Discount: 0%

Final Price: 450.0 EGP

Invoice saved to invoice.txt





---

## ▶️ How To Run

### 🔹 Using NetBeans IDE:
1. Open project folder
2. Run `Main.java`

### 🔹 Using Terminal:

```bash
javac src/app/Main.java
java app.Main




⭐ Future Enhancements

🛢 Save users and orders to a database
🔑 Login system & authentication
📦 Inventory management
📄 Export invoice as PDF

🤝 Contributing

Feel free to fork, improve, and submit a PR 👍
Suggestions are welcome.

👨‍💻 Author

Mohamed M. Mugrabhi
Software Engineering Student — Assiut University
GitHub: https://github.com/mohamedmugrabhi
