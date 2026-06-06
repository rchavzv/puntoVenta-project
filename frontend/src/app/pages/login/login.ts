import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  username = '';
  password = '';

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {}

  login() {
    const body = {
      username: this.username,
      password: this.password,
    };

    this.http
      .post('http://192.168.100.59:8080/auth/login', body, { responseType: 'text' })

      .subscribe({
        next: (response) => {
          console.log(response);
          //alert('Login Correcto');
          this.router.navigate(['/dashboard']);
        },

        error: (err) => {
          console.log(err);
          alert('Usuario o contraseña incorrectos');
        },
      });
  }
}
