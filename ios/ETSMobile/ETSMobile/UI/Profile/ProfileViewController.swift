//
//  ProfileViewController.swift
//  ETS-mobile
//
//  Created by Emmanuel on 2018-04-21.
//  Copyright © 2018 applETS. All rights reserved.
//

import UIKit

class ProfileViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var sectionTitle: UINavigationItem!
    @IBOutlet weak var tableView: UITableView!
    
    let semestres = ["Automne 2018", "Été 2018", "Printemps 2018", "Hiver 2017"]
    
    var currentCourse: String?
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 200.0;//Choose your custom row height
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return semestres.count
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "schoolTerm", for: indexPath) as? SchoolTermSection {
            cell.delegate = self
            return cell
        }
        return UITableViewCell()
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        guard let header = tableView.dequeueReusableHeaderFooterView(withIdentifier: ETSTableHeaderFooter.reuseIdentifer) as? ETSTableHeaderFooter else {
            return nil
        }
        header.customLabel.text = semestres[section]
        
        return header
    }
    
    func tableView(_ tableView: UITableView, willDisplayHeaderView view: UIView, forSection section: Int) {
        if let header = view as? UITableViewHeaderFooterView {
            header.backgroundView?.backgroundColor = UIColor.init(red: 0.25, green: 0.25, blue: 0.25, alpha: 1)
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.rowHeight = UITableView.automaticDimension
        tableView.estimatedRowHeight = 80
        tableView.tableFooterView = UIView()
        
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(ETSTableHeaderFooter.self, forHeaderFooterViewReuseIdentifier: ETSTableHeaderFooter.reuseIdentifer)
        tableView.sectionHeaderHeight = 50
        sectionTitle.title = "Profil"

        
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    
    public func goToCourse(course:String){
        currentCourse=course
        self.performSegue(withIdentifier: "goToCourse", sender: self)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "goToCourse" {
            if let controller = segue.destination as? CourseViewController {
                 controller.title = self.currentCourse!
            }
           
        }
    }
    
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
