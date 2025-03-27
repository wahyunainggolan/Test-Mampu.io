## Running the Application

1. Make sure PostgreSQL is installed and running
2. Create a database named `wallet_db`
3. Update the database credentials in `application.properties`
4. Run the Spring Boot application:
   ``` mvn spring-boot:run ```
5. The application will be available at `http://localhost:8080`

## API Endpoints

1. **Withdraw API**
   - Method: POST
   - Endpoint: `/api/wallet/withdraw`
   - Parameters:
     - `username` (String): The username of the user
     - `amount` (double): The amount to withdraw
   - Success Response: "Withdrawal successful"
   - Error Responses:
     - "User not found with username: [username]"
     - "Insufficient balance for withdrawal"

2. **Balance Inquiry API**
   - Method: GET
   - Endpoint: `/api/wallet/balance`
   - Parameters:
     - `username` (String): The username of the user
   - Success Response: "Current balance: [balance]"
   - Error Response: "User not found with username: [username]"
