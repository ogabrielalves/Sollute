import axios from 'axios';

const urlBase = 'http://localhost:8080/empresas';
const headers = {
  'Content-Type': 'application/json'
};
class RegisterService {
  constructor() {
    this.state = {
      data: []
    };
  }

  async postEmpresa(obj) {
    return await axios.post(`${urlBase}`, 
      obj
    )
      .then(res => res.data)
      .catch((err) => {
        console.error(`request failed ${err}`);
      });
  }

}


export default RegisterService;