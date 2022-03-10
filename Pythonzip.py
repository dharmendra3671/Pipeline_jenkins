import json
import time
import sys
import zipfile
import os
import glob
with open(r'config.json','r') as filesdata:
    filedata=json.load(filesdata)
for file_ele in filedata['data1']:
        #print("file element",f_ele)
        

         list_of_files = filter( lambda x: os.path.isfile(os.path.join(file_ele['path'], x)),
                                 os.listdir(file_ele['path']))

         list_of_files = sorted( list_of_files,
                                 key = lambda x: os.path.getmtime(os.path.join(file_ele['path'], x)))

         if(len(list_of_files)==0):
             print("Files are not present to do further actions")
             sys.exit(0)
            
         else:
             fileno=file_ele['file']
             list_of_files.reverse()
             print("The Update Files are:")
             for index, file in enumerate(list_of_files):
                 if index > (fileno-1):
                     os.remove(os.path.join(file_ele['path'],file))
                 else:
                     file_path = os.path.join(file_ele['path'],file)
                     timestamp_str = time.strftime('%d/%m/%Y :: %H:%M:%S',
                                             time.gmtime(os.path.getmtime(file_ele['path'])))
                     print(timestamp_str, ' -->', file)

             path=file_ele['path']
             def prepare_zip(path):
                 new_file = file_ele['path']+'.zip'
                
                 zip = zipfile.ZipFile(new_file, 'w', zipfile.ZIP_DEFLATED)
                
                 for dir_file, dir_names, files in os.walk(file_ele['path']):
                     f_path = dir_file.replace(dir_file, '')
                     f_path = f_path and f_path + os.sep
                    
                    
                     for file in files:
                         zip.write(os.path.join(file_ele['path'], file), f_path + file)
                 zip.close()
                 return os.rename(new_file,file_ele['Zip_file_name'])
             os.chdir(file_ele['subdir'])
             for file in glob.glob("*.zip"):
                 if os.path.exists(file):
                     os.remove(os.path.join(file_ele['subdir'], file))
                 else:
                     sys.exit(0)
             else:
                 prepare_zip(file_ele['path'])
