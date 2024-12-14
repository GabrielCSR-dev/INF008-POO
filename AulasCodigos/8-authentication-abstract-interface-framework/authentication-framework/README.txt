package inf008.ecommerce.interfaces;
package inf008.ecommerce.plugins;


IPlugin
   * abstract boolean load();
   * abstract boolean unload();
   IAuthenticationPlugin
      * abstract boolean signIn(String username, String password);
      * abstract boolean signOut();
      DatabaseAuthenticationPlugin
      GoogleAuthenticationPlugin
      AppleIdAuthenticationPlugin
   ISerializationPlugin
      * abstract boolean save();
      * abstract boolean load();
      FileSystemSerializationPlugin
      DatabaseSerializationPlugin
   IExportPlugin
      * abstract boolean export();
      CvsExportPlugin
      PdfExportPlugin
      HtmlExportPlugin
