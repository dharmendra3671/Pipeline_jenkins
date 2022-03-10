import json
import os
import glob

from artifactory import ArtifactoryPath

with open(r'artifact_jf.json','r') as filesdata:
    filedata=json.load(filesdata)
    for file_ele in filedata['data1']:
        def filename():
            os.chdir(file_ele["file_path"])
            for file in glob.glob("*.zip"):
                if os.path.exists(file):
                    return file
        path1 = ArtifactoryPath(file_ele["repo_link"],
            auth=(file_ele["username"],file_ele["password"]))
        path1.touch()



        path1.deploy_file(filename())
