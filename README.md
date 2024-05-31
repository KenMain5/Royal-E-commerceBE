<h2>Description</h2>

This is the backend for Royal, an exclusive clothing store app. 

<h2>Features</h2>

<div>
  <ul>
    <li>Implements Spring Security to provide comprehensive security measures, including advanced authentication and authorization through JWTs (JSON Web Tokens).</li>
    <li>Ensures reliable and consistent operations, crucial for order processing and inventory management.</li>
    <li>Utilizes JPA for efficient and effective database interactions, ensuring optimal performance and scalability.</li>
    <li>Features a comprehensive global exception handler that streamlines error management, providing clear, actionable feedback and maintaining stability.</li>


  </ul>
</div>


## Installation
<a id='install'></a>

1. Clone the repo
```
git clone https://github.com/KenMain5/Royal-E-commerce.git
```
2. Install NPM packages
```
npm install
```
3. Run the application
```
npm start
```



# Endpoints
<details>
  <summary>GET /</summary>
  <br>
  <div>
	This is the endpoint that the client request gets when they access the website. The server sends the main page of the website. 
  </div>
  <br>
</details>

<details>
  <summary>POST /register</summary>
  <br>
  <div>
    What happens in the server side, it validates the input received from the client, checks if the email is currently being used, if not, it hashes         the password and stores all the information along with the hashed password by doing a Parametized SQL Query.
  </div>
  <br>
</details>


<details>
  <summary>POST /signin</summary>
  <br>
  <div>
    What happens in the server side, is that it grabs the hashed password that goes along with the username that the client sends, afterwards, we use 
    the BCrypt method to compare the two password hashes. If it is successful, then the user would be logged in. 
  </div>
  <br>
</details>


# Takeaway
This is one of the projects I'm really proud of because it's very fun to do and I learned a lot. 
  
How to deploy a PostGreSQL database(using SupaBase). 
  
Parametized SQL Query (to defend better from Script Attacks)
  
How passwords are supposed to be stored in databases (Salting and Hashing).
  
Had more practice with Asynchronous functions(Async, Await, Catch, Then)
  
One thing I do miss though is working and coding with other people in software projects. 
