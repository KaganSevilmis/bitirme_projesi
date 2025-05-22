// Auth durumunu yönetmek için basit bir store
export const auth = {
  state: {
    isAuthenticated: false,
    user: null
  },

  login(userData) {
    this.state.isAuthenticated = true;
    this.state.user = userData;
    // Local storage'a kaydet
    localStorage.setItem('isAuthenticated', 'true');
    localStorage.setItem('user', JSON.stringify(userData));
  },

  logout() {
    this.state.isAuthenticated = false;
    this.state.user = null;
    // Local storage'dan temizle
    localStorage.removeItem('isAuthenticated');
    localStorage.removeItem('user');
  },

  // Sayfa yenilendiğinde auth durumunu kontrol et
  checkAuth() {
    const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true';
    const user = JSON.parse(localStorage.getItem('user'));
    
    this.state.isAuthenticated = isAuthenticated;
    this.state.user = user;
    
    return isAuthenticated;
  }
}; 