import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  username = '';
  password = '';

  constructor(private http: HttpClient) {}

  login() {
    const body = {
      username: this.username,
      password: this.password,
    };

    this.http
      .post('http://192.168.100.59:8080/auth/login', body, { responseType: 'text' })

      .subscribe({
        next: (response) => {
          console.log('RESPUESTA');
          console.log(response);

          alert('Login OK');
        },

        error: (err) => {
          console.log('ERROR COMPLETO');
          console.log(err);

          if (err.error) {
            console.log('Respuesta backend:');
            console.log(err.error);
          }
          alert('Error Login');
        },
      });
  }
}
