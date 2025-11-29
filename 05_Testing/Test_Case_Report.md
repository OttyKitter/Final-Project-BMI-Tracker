**Test Case Report**

1. **คำนวณ BMI ปกติ**

	**ประเภท : Success**

	**Input : Weight : 70 , Height: 175**

	**ผลลัพธ์ที่คาดหวัง : BMI: 22.9 , Category: Normal , ข้อความ: Record saved successfully\!**

	**ผลลัพธ์จริง : Pass**

2. **Min Height**

	**ประเภท : Boundary**

	**Input : Weight : 20 , Height: 160**

	**ผลลัพธ์ที่คาดหวัง : บันทึกข้อมูลสำเร็จ , BMI คำนวณได้ตามปกติ**

	**ผลลัพธ์จริง : Pass**

3. **น้ำหนักต่ำกว่าเกณฑ์**

	**ประเภท : Error**

	**Input :  Weight: 19.9 , Height: 160**

	**ผลลัพธ์ที่คาดหวัง : แสดง Error: Invalid weight\! Please enter weight between 20.0 and 600.0 kg.  , ไม่มีการบันทึกข้อมูล**

	**ผลลัพธ์จริง : Pass**

	

4. **Max Height**

	**ประเภท : Boundary**

	**Input : Weight : 80 , Height: 300**

	**ผลลัพธ์ที่คาดหวัง : บันทึกข้อมูลสำเร็จ**

	**ผลลัพธ์จริง : Pass**

5. **ส่วนสูงเกินเกณฑ์**

	**ประเภท : Error**

	**Input : Weight : 80 , Height: 301**

	**ผลลัพธ์ที่คาดหวัง : แสดง Error : Invalid height\! Please enter height between 50.0 and 300.0 cm.**

	**ผลลัพธ์จริง : Pass**

6. **Input ไม่ใช่ตัวเลข** 

	**ประเภท : Error**

	**Input : Weight : หกสิบ , Height: 170**

	**ผลลัพธ์ที่คาดหวัง : จับ Exception และแจ้งเตือน Please enter a valid number , โปรแกรมไม่ Crash**

	**ผลลัพธ์จริง : Pass**

7. **ตรวจสอบ Logic เกณฑ์อ้วน**

	**ประเภท : Success**

	**Input : Weight: 90 , Height: 170**

	**ผลลัพธ์ที่คาดหวัง : คำนวณ BMI \= 31.1 , Category: Obese** 

	**ผลลัพธ์จริง Pass**

8. **การแสดงผลกราฟ**

	**ประเภท : Success**

	**Input : มีข้อมูลในระบบ 3 รายการ**

	**ผลลัพธ์ที่คาดหวัง : แสดง ASCII Chart โดยมีการคำนวณแกน Y และพล็อตจุดได้ถูกต้อง**

	**ผลลัพธ์จริง : Pass**

9. **การลบข้อมูล**

	**ประเภท : Success**

	**Input : กดHistory จากนั้นลบข้อมูล**

	**ผลลัพธ์ที่คาดหวัง : แสดงข้อความ Record deleted successfully\! . ข้อมูลนั้นถูกลบ**

**ผลลัพธ์จริง : Pass**

10. **ตรวจสอบ Persistence** 

	**ประเภท : Success**

	**Input : เพิ่มข้อมูล , ปิดโปรแกรม , เปิดใหม่**

	**ผลลัพธ์ที่คาดหวัง : ข้อมูลที่บันทึกไว้ก่อนปิดโปรแกรมยังคงอยู่**

	**ผลลัพธ์จริง Pass**